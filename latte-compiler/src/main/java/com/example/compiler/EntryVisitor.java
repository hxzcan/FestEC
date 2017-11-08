package com.example.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 */

public final class EntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {
    private Filer mFiler=null;//需要遍历的东西
    private TypeMirror typeMirror=null;//要循环找到的类型
    private String mPackageName=null;//最终的包名

    public void setmFiler(Filer mFiler) {
        this.mFiler = mFiler;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName=s;
        return aVoid;
    }

    //找到标注的原信息
    @Override
    public Void visitType(TypeMirror t, Void aVoid) {
        typeMirror=t;
        return aVoid ;
    }

    private void generatorJavaCode(){
        final TypeSpec targetActivity=TypeSpec
                .classBuilder("WXEntryActivity")//微信必须是这个名字
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(typeMirror))
                .build();
        final JavaFile javaFile=JavaFile
                .builder(mPackageName+".wxapi",targetActivity)
                .addFileComment("微信入口文件")
                .build();
        //写入
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
