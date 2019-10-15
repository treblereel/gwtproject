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
package org.gwtproject.i18n.client;

import com.google.gwt.core.client.GWT;
import org.gwtproject.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.junit.client.GWTTestCase;
import org.gwtproject.i18n.shared.Localizable;

/**
 * Tests annotations not covered elsewhere.
 */
public class AnnotationsTest extends GWTTestCase {

    /**
     * First grandparent for test.
     */
    public interface GP1 extends TestConstants {
        @Constants.DefaultStringValue("gp1 annot")
        String gp1();

        @Constants.DefaultStringValue("gp1 shared annot")
        String shared();
    }

    /**
     * Second grandparent for test.
     */
    public interface GP2 extends TestConstants {
        @Constants.DefaultStringValue("gp2 annot")
        String gp2();

        @Constants.DefaultStringValue("gp2 shared annot")
        String shared();
    }

    /**
     * Test interface for P1 before P2.
     */
    @Localizable.IsLocalizable
    public interface Inherit1 extends P1, P2 {
    }

    /**
     * Test interface for P2 before P1.
     */
    @Localizable.IsLocalizable
    public interface Inherit2 extends P2, P1 {
        @Constants.DefaultStringValue("def")
        String def();
    }

    /**
     * Used to verify that we can explicitly localize messages in annotations.
     */
    @DefaultLocale("en")
    public interface Inherit2_en extends Inherit2 {
        @Override
        @Constants.DefaultStringValue("en def")
        String def();
    }

    /**
     * First parent interface for test.
     */
    public interface P1 extends TestConstants {
        @Constants.DefaultStringValue("p1 annot")
        String p1();

        @Constants.DefaultStringValue("p1 shared annot")
        String shared();
    }

    /**
     * Second parent interface for test.
     */
    public interface P2 extends GP1, GP2 {
        @Constants.DefaultStringValue("p2 annot")
        String p2();

        @Override
        String shared();
    }

    /**
     * Basic test message.
     */
    public interface Msg1 extends Messages {
        @DefaultMessage("Test {0}")
        String getTest(String testName);
    }

    /**
     * Plural form test message.
     */
    public interface Msg2 extends Messages {
        @DefaultMessage("You have {0} widgets.")
        @PluralText({"one", "You have a widget."})
        String getWidgetCount(@PluralCount int count);

        @DefaultMessage("from en")
        String leastDerived();
    }

    /**
     * Aggregate messages into one interface.
     */
    @Localizable.IsLocalizable
    public interface AllMessages extends Msg1, Msg2 {
    }

    @Override
    public String getModuleName() {
        return null;//"org.gwtproject.i18n.I18NTest_en";
    }

    public void testInheritance() {
        Inherit1 i1 = Inherit1_Factory.create();
        assertEquals("p1 annot", i1.p1());
        assertEquals("gp2 annot", i1.gp2());
        assertEquals("p1 shared annot", i1.shared());
        Inherit2 i2 = Inherit2_Factory.create();
        assertEquals("p1 annot", i2.p1());
        assertEquals("gp2 annot", i2.gp2());
        assertEquals("gp1 shared annot", i2.shared());

        // TODO(jat): this doesn't work because findDerivedClasses only
        // looks for concrete classes, not other interfaces -- commenting
        // out for now, revisit later.
        // assertEquals("en def", i2.def());
    }

    public void testIssue2359() {
        AllMessages m = AllMessages_Factory.create();
        assertEquals("Test foo", m.getTest("foo"));
        assertEquals("You have 2 widgets.", m.getWidgetCount(2));
        assertEquals("You have a widget.", m.getWidgetCount(1));
    }

    public void testLeastDerived() {
        AllMessages m = AllMessages_Factory.create();
        assertEquals("from en_US", m.leastDerived());
    }
}
