package org.gwtproject.validation.rebind.beaninfo;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Payload;

import org.gwtproject.validation.rebind.beaninfo.impl.ConstraintDescriptorImpl;

/**
 * Describes a single constraint and its composing constraints.
 * <code>T</code> is the constraint's annotation type.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public interface ConstraintDescriptor {
    /**
     * Returns the annotation describing the constraint declaration.
     * If a composing constraint, attribute values are reflecting
     * the overridden attributes of the composing constraint
     *
     * @return The annotation for this constraint.
     */
    String getAnnotation();

    /**
     * The set of groups the constraint is applied on.
     * If the constraint declares no group, a set with only the <code>Default</code>
     * group is returned.
     *
     * @return The groups the constraint is applied on.
     */
    Object[] getGroups();

    /**
     * The set of payload the constraint hosts.
     *
     * @return payload classes hosted on the constraint or an empty set if none.
     */
    Set<Class<? extends Payload>> getPayload();

    /**
     * List of the constraint validation implementation classes.
     *
     * @return list of the constraint validation implementation classes.
     */
    List<Class<? extends ConstraintValidator>>
    getConstraintValidatorClasses();

    /**
     * Returns a map containing the annotation attribute names as keys and the
     * annotation attribute values as value.
     * If this constraint is used as part of a composed constraint, attribute
     * values are reflecting the overridden attribute of the composing constraint.
     *
     * @return a map containing the annotation attribute names as keys
     *         and the annotation attribute values as value.
     */
    //TODO replace it from impl
    Map<String, ConstraintDescriptorImpl.DefaultValueHolder> getAttributes();

    /**
     * Return a set of composing <code>ConstraintDescriptor</code>s where each
     * descriptor describes a composing constraint. <code>ConstraintDescriptor</code>
     * instances of composing constraints reflect overridden attribute values in
     * {@link #getAttributes()}  and {@link #getAnnotation()}.
     *
     * @return a set of <code>ConstraintDescriptor<code> objects or an empty set
     *         in case there are no composing constraints.
     */
    Set<ConstraintDescriptor> getComposingConstraints();

    /**
     * @return true if the constraint is annotated with <code>@ReportAsSingleViolation</code>
     */
    boolean isReportAsSingleViolation();
}
