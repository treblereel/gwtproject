package org.gwtproject.validation.context;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 10/26/18.
 */
public class AptContext {

    public final Messager messager;
    public final Filer filer;
    public final Elements elements;
    public final Types types;
    public final RoundEnvironment roundEnvironment;
    public final ProcessingEnvironment processingEnv;

    private final Set<Class<? extends Annotation>> constraints = new HashSet<>();

    {
        constraints.add(AssertFalse.class);
        constraints.add(AssertTrue.class);
        constraints.add(DecimalMax.class);
        constraints.add(DecimalMin.class);
        constraints.add(Digits.class);
        constraints.add(Future.class);
        constraints.add(Max.class);
        constraints.add(Min.class);
        constraints.add(NotNull.class);
        constraints.add(Null.class);
        constraints.add(Past.class);
        constraints.add(Pattern.class);
        constraints.add(Size.class);
        constraints.add(Valid.class);
    }

    public AptContext(final ProcessingEnvironment processingEnv, final RoundEnvironment roundEnvironment) {
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
        this.elements = processingEnv.getElementUtils();
        this.types = processingEnv.getTypeUtils();
        this.roundEnvironment = roundEnvironment;
        this.processingEnv = processingEnv;
    }

    public Set<Class<? extends Annotation>> getConstraints() {
        return constraints;
    }
}