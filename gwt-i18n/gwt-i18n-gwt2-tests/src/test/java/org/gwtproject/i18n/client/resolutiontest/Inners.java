/*
 * Copyright 2008 Google Inc.
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

package org.gwtproject.i18n.client.resolutiontest;

import java.util.Map;
import org.gwtproject.i18n.client.Constant;
import org.gwtproject.i18n.client.Constants;
import org.gwtproject.i18n.client.Localizable;
import org.gwtproject.i18n.client.Messages;
import org.gwtproject.i18n.client.TestConstants;
import org.gwtproject.i18n.client.resolutiontest.Inners.InnerClass.ProtectedInnerInnerClass.ExtendsAnotherInner;
import org.gwtproject.i18n.client.resolutiontest.Inners.InnerClass.ProtectedInnerInnerClass.ExtendsAnotherInner.ExtendProtectedInner;
import org.gwtproject.i18n.client.resolutiontest.Inners.OuterLoc.InnerLoc;

/** Test class. */
public class Inners {

  public static String testInnerLoc() {
    // Check being able to create inner
    InnerLoc loc = new OuterLoc.InnerLoc_();
    return loc.string();
  }

  /** Check binding to static inner. */
  public static class OuterLoc implements Localizable {
    public String string() {
      return "default";
    }

    /** Test Inner Localizable. */
    protected abstract static class InnerLoc implements Localizable {
      public abstract String string();
    }

    /** Inner Localizable Impl. */
    protected static class InnerLoc_ extends InnerLoc {
      @Override
      public String string() {
        return "InnerLoc";
      }
    }
  }

  /** Test Extension of Inner Inner. */
  @Constant
  public interface ExtendsInnerInner extends InnerClass.InnerInner {
    String extendsInnerInner();
  }

  /** Test inner localizable. */
  public interface LocalizableSimpleInner extends Localizable {
    String getLocalizableInner();
  }

  /** Tests Localizable, Must be static. */
  public static class LocalizableSimpleInner_ implements LocalizableSimpleInner {

    @Override
    public String getLocalizableInner() {
      return "getLocalizableInner";
    }
  }

  /** Test embedded constant. */
  @Constant
  public interface HasInner extends SimpleInner {

    /** Test inner or constant. */
    @Constant
    interface IsInner extends Constants {
      int isInner();
    }

    String hasInner();
  }

  /** Inner class. */
  public static class InnerClass {

    /** Test inner inner,abstract,and static localizable. */
    public abstract static class LocalizableInnerInner implements Localizable {
      public abstract String string();
    }

    /** Test inner inner and static localizable. */
    public static class LocalizableInnerInner_ extends LocalizableInnerInner {
      @Override
      public String string() {
        return "localizableInnerInner";
      }
    }

    /**
     * Messages use the same resolution mechanisms as constants, but including a couple of message
     * cases to sanity check.
     */
    public interface InnerInnerMessages extends Messages {
      String innerClassMessages(String a);
    }

    /** Test inner interface of inner interface. */
    @Constant
    public interface InnerInner extends Outer {
      float innerInner();
    }

    /** OuterLoc piglatin binding. */
    public static class OuterLoc_piglatin extends OuterLoc {
      @Override
      public String string() {
        return "piglatin";
      }
    }

    /** Tests Protected Inner Class. */
    public Map<String, String> testExtendsAnotherInner() {
      ExtendsAnotherInner clazz =
          InnersInnerClassProtectedInnerInnerClassExtendsAnotherInnerFactory.get();
      Map<String, String> answer = clazz.extendsAnotherInner();
      return answer;
    }

    /** Test for ExtendProtectedInner. */
    public String testExtendsProtectedInner() {
      ExtendProtectedInner inner =
          InnersInnerClassProtectedInnerInnerClassExtendsAnotherInnerExtendProtectedInnerFactory
              .get();
      return inner.extendProtectedInner();
    }

    /** Another inner class. */
    protected static class ProtectedInnerInnerClass {

      /** Test extension. */
      @Constant
      public interface ExtendsAnotherInner extends InnerInner {

        /** Test protected extension. */
        @Constant
        interface ExtendProtectedInner extends ProtectedInner {
          String extendProtectedInner();
        }

        /** Test maps in extension. */
        Map<String, String> extendsAnotherInner();
      }

      /** Checks inner inner and across packages. */
      public interface InnerInnerInner extends TestConstants {
        String[] innerInnerInner();
      }

      /**
       * Messages use the same resolution mechanisms as constants, but including a couple of message
       * cases to sanity check.
       */
      @Constant
      public interface InnerInnerInnerMessages extends InnerInnerMessages {
        @Override
        String innerClassMessages(String a);
      }
    }
  }

  /** Tests Simple Constant inheritance. */
  @Constant
  public interface SimpleInner extends TestConstants {
    String simpleInner();
  }

  /** Boolean return from protected inner. */
  @Constant
  protected interface ProtectedInner extends TestConstants {
    boolean protectedInner();
  }

  /** Testing protected inner. */
  public boolean testProtectedInner() {
    ProtectedInner inner =
        InnersInnerClassProtectedInnerInnerClassExtendsAnotherInnerExtendProtectedInnerFactory
            .get();
    return inner.protectedInner();
  }
}

/** Used to test extension from package protected file. */
@Constant
interface Outer extends Constants {
  String outer();
}
