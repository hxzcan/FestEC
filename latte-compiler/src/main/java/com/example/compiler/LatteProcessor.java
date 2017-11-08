package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 */
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {


    private Set<String> getSupportAnnotationsTypes(){
        final Set<String> types=new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> annotations=getSupportAnnotations();
        for (Class<? extends Annotation> annotation : annotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations(){
        final Set<Class<? extends Annotation>> annotations=new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generatorEntryCode(roundEnvironment);
        generatorAppRegisterCode(roundEnvironment);
        generatorPayEntryCode(roundEnvironment);
        return true;
    }

    /**
     * 扫描注解类
     * @param environment
     * @param annotations
     * @param valueVisitor
     */
    private void scan(RoundEnvironment environment, Class<? extends Annotation> annotations,
                      AnnotationValueVisitor valueVisitor){
        for (Element typeElements : environment.getElementsAnnotatedWith(annotations)) {
            final List<? extends AnnotationMirror> annotationMirrors=typeElements.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror :annotationMirrors) {
                final Map<? extends ExecutableElement,? extends AnnotationValue>
                        map=annotationMirror.getElementValues();
                for (Map.Entry<? extends ExecutableElement,? extends AnnotationValue> entry:
                        map.entrySet()){
                    entry.getValue().accept(valueVisitor,null);
                }
            }
        }
    }

    private void generatorEntryCode(RoundEnvironment environment){
        EntryVisitor entryVisitor=new EntryVisitor();
        entryVisitor.setmFiler(processingEnv.getFiler());
        scan(environment,EntryGenerator.class,entryVisitor);
    }

    private void generatorAppRegisterCode(RoundEnvironment environment){
        AppRegisterVisitor appRegisterVisitor=new AppRegisterVisitor();
        appRegisterVisitor.setmFiler(processingEnv.getFiler());
        scan(environment,AppRegisterGenerator.class,appRegisterVisitor);
    }

    private void generatorPayEntryCode(RoundEnvironment environment){
        PayEntryVisitor payEntryVisitor=new PayEntryVisitor();
        payEntryVisitor.setmFiler(processingEnv.getFiler());
        scan(environment,PayEntryGenerator.class,payEntryVisitor);
    }
}
