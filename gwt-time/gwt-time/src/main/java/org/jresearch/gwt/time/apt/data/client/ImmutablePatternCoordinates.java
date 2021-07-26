package org.jresearch.gwt.time.apt.data.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.jresearch.gwt.time.apt.base.Chrono;

/**
 * Immutable implementation of {@link PatternCoordinates}.
 *
 * <p>Use the builder to create immutable instances: {@code ImmutablePatternCoordinates.builder()}.
 * Use the static factory method to create immutable instances: {@code
 * ImmutablePatternCoordinates.of()}.
 */
@SuppressWarnings({"all"})
public final class ImmutablePatternCoordinates implements PatternCoordinates {
  private final Chrono chrono;
  private final Locale locale;

  private ImmutablePatternCoordinates(Chrono chrono, Locale locale) {
    this.chrono = Objects.requireNonNull(chrono, "chrono");
    this.locale = Objects.requireNonNull(locale, "locale");
  }

  private ImmutablePatternCoordinates(
      ImmutablePatternCoordinates original, Chrono chrono, Locale locale) {
    this.chrono = chrono;
    this.locale = locale;
  }

  /** @return The value of the {@code chrono} attribute */
  @Override
  public Chrono chrono() {
    return chrono;
  }

  /** @return The value of the {@code locale} attribute */
  @Override
  public Locale locale() {
    return locale;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PatternCoordinates#chrono()
   * chrono} attribute. A value equality check is used to prevent copying of the same value by
   * returning {@code this}.
   *
   * @param value A new value for chrono
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePatternCoordinates withChrono(Chrono value) {
    if (this.chrono == value) return this;
    Chrono newValue = Objects.requireNonNull(value, "chrono");
    if (this.chrono.equals(newValue)) return this;
    return new ImmutablePatternCoordinates(this, newValue, this.locale);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PatternCoordinates#locale()
   * locale} attribute. A shallow reference equality check is used to prevent copying of the same
   * value by returning {@code this}.
   *
   * @param value A new value for locale
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePatternCoordinates withLocale(Locale value) {
    if (this.locale == value) return this;
    Locale newValue = Objects.requireNonNull(value, "locale");
    return new ImmutablePatternCoordinates(this, this.chrono, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutablePatternCoordinates} that have equal
   * attribute values.
   *
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePatternCoordinates
        && equalTo((ImmutablePatternCoordinates) another);
  }

  private boolean equalTo(ImmutablePatternCoordinates another) {
    return chrono.equals(another.chrono) && locale.equals(another.locale);
  }

  /**
   * Computes a hash code from attributes: {@code chrono}, {@code locale}.
   *
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + chrono.hashCode();
    h += (h << 5) + locale.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code PatternCoordinates} with attribute values.
   *
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "PatternCoordinates{" + "chrono=" + chrono + ", locale=" + locale + "}";
  }

  /**
   * Construct a new immutable {@code PatternCoordinates} instance.
   *
   * @param chrono The value for the {@code chrono} attribute
   * @param locale The value for the {@code locale} attribute
   * @return An immutable PatternCoordinates instance
   */
  public static ImmutablePatternCoordinates of(Chrono chrono, Locale locale) {
    return new ImmutablePatternCoordinates(chrono, locale);
  }

  /**
   * Creates an immutable copy of a {@link PatternCoordinates} value. Uses accessors to get values
   * to initialize the new immutable instance. If an instance is already immutable, it is returned
   * as is.
   *
   * @param instance The instance to copy
   * @return A copied immutable PatternCoordinates instance
   */
  public static ImmutablePatternCoordinates copyOf(PatternCoordinates instance) {
    if (instance instanceof ImmutablePatternCoordinates) {
      return (ImmutablePatternCoordinates) instance;
    }
    return ImmutablePatternCoordinates.builder().from(instance).build();
  }

  /**
   * Creates a builder for {@link ImmutablePatternCoordinates ImmutablePatternCoordinates}.
   *
   * <pre>
   * ImmutablePatternCoordinates.builder()
   *    .chrono(org.jresearch.gwt.time.apt.base.Chrono) // required {@link PatternCoordinates#chrono() chrono}
   *    .locale(Locale) // required {@link PatternCoordinates#locale() locale}
   *    .build();
   * </pre>
   *
   * @return A new ImmutablePatternCoordinates builder
   */
  public static ImmutablePatternCoordinates.Builder builder() {
    return new ImmutablePatternCoordinates.Builder();
  }

  /**
   * Builds instances of type {@link ImmutablePatternCoordinates ImmutablePatternCoordinates}.
   * Initialize attributes and then invoke the {@link #build()} method to create an immutable
   * instance.
   *
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or
   * collection, but instead used immediately to create instances.</em>
   */
  public static final class Builder {
    private static final long INIT_BIT_CHRONO = 0x1L;
    private static final long INIT_BIT_LOCALE = 0x2L;
    private long initBits = 0x3L;

    private Chrono chrono;
    private Locale locale;

    private Builder() {}

    /**
     * Fill a builder with attribute values from the provided {@code PatternCoordinates} instance.
     * Regular attribute values will be replaced with those from the given instance. Absent optional
     * values will not replace present values.
     *
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(PatternCoordinates instance) {
      Objects.requireNonNull(instance, "instance");
      chrono(instance.chrono());
      locale(instance.locale());
      return this;
    }

    /**
     * Initializes the value for the {@link PatternCoordinates#chrono() chrono} attribute.
     *
     * @param chrono The value for chrono
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder chrono(Chrono chrono) {
      this.chrono = Objects.requireNonNull(chrono, "chrono");
      initBits &= ~INIT_BIT_CHRONO;
      return this;
    }

    /**
     * Initializes the value for the {@link PatternCoordinates#locale() locale} attribute.
     *
     * @param locale The value for locale
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder locale(Locale locale) {
      this.locale = Objects.requireNonNull(locale, "locale");
      initBits &= ~INIT_BIT_LOCALE;
      return this;
    }

    /**
     * Builds a new {@link ImmutablePatternCoordinates ImmutablePatternCoordinates}.
     *
     * @return An immutable instance of PatternCoordinates
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutablePatternCoordinates build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePatternCoordinates(null, chrono, locale);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_CHRONO) != 0) attributes.add("chrono");
      if ((initBits & INIT_BIT_LOCALE) != 0) attributes.add("locale");
      return "Cannot build PatternCoordinates, some of required attributes are not set "
          + attributes;
    }
  }
}
