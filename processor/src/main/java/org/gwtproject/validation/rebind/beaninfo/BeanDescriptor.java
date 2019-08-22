package org.gwtproject.validation.rebind.beaninfo;

import java.util.Set;

/**
 * Describes a constrained Java Bean and the constraints associated to it.
 *
 * @author Emmanuel Bernard
 */
public interface BeanDescriptor extends ElementDescriptor {
    /**
     * Returns <code>true</code> if the bean involves validation:
     * <ul>
     * <li> a constraint is hosted on the bean itself </li>
     * <li> a constraint is hosted on one of the bean properties</li>
     * <li> or a bean property is marked for cascade (<code>@Valid</code>)</li>
     * </ul>
     *
     * @return <code>true</code> if the bean involves validation, <code>false</code> otherwise.
     */
    boolean isBeanConstrained();

    /**
     * Return the property descriptor for a given property.
     * Return <code>null</code> if the property does not exist or has no
     * constraint nor is marked as cascaded (see {@link #getConstrainedProperties()} )
     * <p/>
     * The returned object (and associated objects including <code>ConstraintDescriptor</code>s)
     * are immutable.
     *
     * @param propertyName property evaluated
     *
     * @return the property descriptor for a given property.
     *
     * @throws IllegalArgumentException if propertyName is null
     */
    PropertyDescriptor getConstraintsForProperty(String propertyName);

    /**
     * Returns a set of property descriptors having at least one constraint defined
     * or marked as cascaded (<code>@Valid<c/ode>). If not property matches,
     * an empty set is returned.
     */
    Set<PropertyDescriptor> getConstrainedProperties();
}

