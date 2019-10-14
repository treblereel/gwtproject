package org.gwtproject.i18n.processor;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;

import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import java.util.Collections;

@AutoService(Processor.class)
public class LocalizableProcessor extends BasicAnnotationProcessor {
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    protected Iterable<? extends ProcessingStep> initSteps() {
        return Collections.singleton(new LocalizableProcessingStep(processingEnv));
    }
}
