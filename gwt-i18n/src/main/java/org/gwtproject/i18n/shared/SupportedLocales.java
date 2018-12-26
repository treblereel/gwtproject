package org.gwtproject.i18n.shared;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
public @interface SupportedLocales {
    String[] value() default {};
}
