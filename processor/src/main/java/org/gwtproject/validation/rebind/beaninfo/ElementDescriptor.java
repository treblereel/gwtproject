package org.gwtproject.validation.rebind.beaninfo;

import java.util.Set;

/**
 * Describes a validated element (class, field or property).
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 * @author Dmitrii Tikhomirov
 * Created by treblereel 8/20/19
 */
public interface ElementDescriptor<T> {

    /**
     * Return <code>true</code> if at least one constraint declaration is present
     * for this element in the class hierarchy, <code>false</code> otherwise.
     */
    boolean hasConstraints();

    /**
     * @return Statically defined returned type.
     */
    T getElementClass();

    String getFullyQualifiedFieldName();

    /**
     * Return all constraint descriptors for this element in the class hierarchy
     * or an empty <code>Set</code> if none are present.
     * @return <code>Set</code> of constraint descriptors for this element
     */
    Set<ConstraintDescriptor> getConstraintDescriptors();
}
