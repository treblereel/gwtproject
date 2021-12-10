/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.i18n.server;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;
import org.gwtproject.safehtml.shared.SafeHtml;

/** A return type or parameter type in a Messages/Constants method. */
public class Type {
  /** An array type. */
  public static class ArrayType extends Type {

    private final Type componentType;

    public ArrayType(String sourceName, Type componentType) {
      super(sourceName, false);
      this.componentType = componentType;
    }

    @Override
    public Type getComponentType() {
      return componentType;
    }
  }

  /** An enum type. */
  public static class EnumType extends Type {

    private final String[] values;

    public EnumType(String sourceName, String[] values) {
      super(sourceName, false);
      this.values = values;
    }

    @Override
    public String[] getEnumValues() {
      return values;
    }

    public int getOrdinal(String name) {
      for (int i = 0; i < values.length; ++i) {
        if (values[i].equals(name)) {
          return i;
        }
      }
      return -1;
    }
  }

  /** A list type. */
  public static class ListType extends Type {

    private final Type componentType;

    public ListType(String sourceName, Type componentType) {
      super(sourceName, false);
      this.componentType = componentType;
    }

    @Override
    public Type getComponentType() {
      return componentType;
    }
  }

  // Singletons for most types, only array/list and generic user objects aren't
  public static final Type BOOLEAN = new Type("boolean", true);

  public static final Type BYTE = new Type("byte", true);

  public static final Type CHAR = new Type("char", true);

  public static final Type DATE = new Type(Date.class.getCanonicalName(), false);

  public static final Type DOUBLE = new Type("double", true);

  public static final Type FLOAT = new Type("float", true);

  public static final Type INT = new Type("int", true);

  public static final Type LONG = new Type("long", true);

  public static final Type NUMBER = new Type(Number.class.getCanonicalName(), false);

  public static final Type OBJECT = new Type(Object.class.getCanonicalName(), false);

  public static final Type SHORT = new Type("short", true);

  public static final Type SAFEHTML = new Type(SafeHtml.class.getCanonicalName(), false);

  public static final Type STRING = new Type(String.class.getCanonicalName(), false);

  public static final Type STRING_MAP =
      new Type(
          Map.class.getCanonicalName()
              + "<"
              + String.class.getCanonicalName()
              + ","
              + String.class.getCanonicalName()
              + ">",
          false);

  private final String sourceName;

  private final boolean isPrimitive;

  public Type(String sourceName, boolean isPrimitive) {
    this.sourceName = sourceName;
    this.isPrimitive = isPrimitive;
  }

  /**
   * Get the component type, if this type is an array or list.
   *
   * @return component type, or null if not an array or list
   */
  public Type getComponentType() {
    return null;
  }

  /**
   * Get the list of value names, if this type is an enum.
   *
   * @return values, or null if not an enum
   */
  public String[] getEnumValues() {
    return null;
  }

  /**
   * Get the name of this type as it would appear in source code.
   *
   * @return source name
   */
  public String getSourceName() {
    return sourceName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Type)) {
      return false;
    }
    Type type = (Type) o;
    return isPrimitive() == type.isPrimitive() && getSourceName().equals(type.getSourceName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSourceName(), isPrimitive());
  }

  @Override
  public String toString() {
    return sourceName;
  }

  public boolean isPrimitive() {
    return isPrimitive;
  }
}
