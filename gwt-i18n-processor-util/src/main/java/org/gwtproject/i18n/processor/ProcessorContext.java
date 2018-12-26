package org.gwtproject.i18n.processor;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashMap;
import java.util.Map;

public class ProcessorContext {

    private final Messager messager;
    private final Filer filer;
    private final Types types;
    private final Elements elements;
    private final ProcessingEnvironment processingEnv;

    public ProcessorContext(ProcessingEnvironment processingEnv) {
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        this.types = processingEnv.getTypeUtils();
        this.elements = processingEnv.getElementUtils();
        this.processingEnv = processingEnv;

    }

    public Messager getMessager() {
        return messager;
    }

    public Filer getFiler() {
        return filer;
    }

    public Types getTypes() {
        return types;
    }

    public Elements getElements() {
        return elements;
    }

    public Map<String, String> getOptions(){
        return new HashMap<>(processingEnv.getOptions());
    }


}
