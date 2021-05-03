package org.gwtproject.validation.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.gwtproject.validation.client.constraints.AssertFalseValidator;
import org.gwtproject.validation.client.constraints.AssertTrueValidator;
import org.gwtproject.validation.client.constraints.DecimalMaxValidatorForNumber;
import org.gwtproject.validation.client.constraints.DecimalMaxValidatorForString;
import org.gwtproject.validation.client.constraints.DecimalMinValidatorForNumber;
import org.gwtproject.validation.client.constraints.DecimalMinValidatorForString;
import org.gwtproject.validation.client.constraints.DigitsValidatorForNumber;
import org.gwtproject.validation.client.constraints.DigitsValidatorForString;
import org.gwtproject.validation.client.constraints.FutureValidatorForDate;
import org.gwtproject.validation.client.constraints.MaxValidatorForNumber;
import org.gwtproject.validation.client.constraints.MaxValidatorForString;
import org.gwtproject.validation.client.constraints.MinValidatorForNumber;
import org.gwtproject.validation.client.constraints.MinValidatorForString;
import org.gwtproject.validation.client.constraints.NotNullValidator;
import org.gwtproject.validation.client.constraints.NullValidator;
import org.gwtproject.validation.client.constraints.PastValidatorForDate;
import org.gwtproject.validation.client.constraints.PatternValidator;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfBoolean;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfByte;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfChar;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfDouble;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfFloat;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfInt;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfLong;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfObject;
import org.gwtproject.validation.client.constraints.SizeValidatorForArrayOfShort;
import org.gwtproject.validation.client.constraints.SizeValidatorForCollection;
import org.gwtproject.validation.client.constraints.SizeValidatorForMap;
import org.gwtproject.validation.client.constraints.SizeValidatorForString;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 8/21/19
 */
public class ConstraintHelper {

    private final Map<String, ConstraintHolder> builtinConstraints = new HashMap<>();

    ConstraintHelper() {
        addConstraint(AssertFalse.class, AssertFalseValidator.class);
        addConstraint(AssertTrue.class, AssertTrueValidator.class);

        addConstraint(DecimalMax.class, Arrays.asList(
                DecimalMaxValidatorForNumber.class,
                DecimalMaxValidatorForString.class
        ));

        addConstraint(DecimalMin.class, Arrays.asList(
                DecimalMinValidatorForNumber.class,
                DecimalMinValidatorForString.class
        ));

        addConstraint(DecimalMin.class, Arrays.asList(
                DigitsValidatorForNumber.class,
                DigitsValidatorForString.class
        ));

        addConstraint(Future.class, FutureValidatorForDate.class);

        addConstraint(Max.class, Arrays.asList(
                MaxValidatorForNumber.class,
                MaxValidatorForString.class
        ));

        addConstraint(Min.class, Arrays.asList(
                MinValidatorForNumber.class,
                MinValidatorForString.class
        ));

        addConstraint(NotNull.class, NotNullValidator.class);

        addConstraint(Null.class, NullValidator.class);

        addConstraint(Past.class, PastValidatorForDate.class);

        addConstraint(Pattern.class, PatternValidator.class);

        addConstraint(Size.class, Arrays.asList(
                SizeValidatorForArrayOfBoolean.class,
                SizeValidatorForArrayOfByte.class,
                SizeValidatorForArrayOfChar.class,
                SizeValidatorForArrayOfDouble.class,
                SizeValidatorForArrayOfFloat.class,
                SizeValidatorForArrayOfInt.class,
                SizeValidatorForArrayOfLong.class,
                SizeValidatorForArrayOfObject.class,
                SizeValidatorForArrayOfShort.class,
                SizeValidatorForCollection.class,
                SizeValidatorForMap.class,
                SizeValidatorForString.class
        ));
    }

    public ConstraintHolder get(String annotation) {
        if (builtinConstraints.containsKey(annotation)) {
            return builtinConstraints.get(annotation);
        }
        return null;
    }

    void addConstraint(String annotation, List<String> clazzz, Set<AnnotationMirror> inheritedConstraint) {
        builtinConstraints.put(annotation, new ConstraintHolder(clazzz.stream().collect(Collectors.toList()), inheritedConstraint));
    }

    private void addConstraint(Class<?> annotation, Class<?> clazz) {
        List<Class<?>> list = new ArrayList<>();
        list.add(clazz);
        addConstraint(annotation, list);
    }

    private void addConstraint(Class<?> annotation, List<Class<?>> clazzz) {
        builtinConstraints.put(annotation.getCanonicalName(), new ConstraintHolder(clazzz.stream().map(Class::getCanonicalName)
                .collect(Collectors.toList())));
    }

    public Set<String> getAnnotations() {
        return builtinConstraints.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    public static class ConstraintHolder {

        private List<String> validators;
        private Set<AnnotationMirror> inheritedConstraint;

        ConstraintHolder(List<String> validators) {
            this.validators = validators;
        }

        ConstraintHolder(List<String> validators, Set<AnnotationMirror> inheritedConstraint) {
            this.validators = validators;
            this.inheritedConstraint = inheritedConstraint;
        }

        public List<String> getValidators() {
            return validators;
        }

        public Set<AnnotationMirror> getInheritedConstraint() {
            return inheritedConstraint != null ? inheritedConstraint : Collections.emptySet();
        }
    }
}
