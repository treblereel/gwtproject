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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.gwtproject.i18n.client.Messages.Example;
import org.gwtproject.i18n.client.Messages.PluralCount;
import org.gwtproject.i18n.client.Messages.Select;
import org.gwtproject.i18n.server.MessageCatalogFactory.Context;
import org.gwtproject.i18n.server.MessageCatalogFactory.Writer;
import org.gwtproject.i18n.server.MessageFormatUtils.MessageStyle;
import org.gwtproject.i18n.shared.GwtLocale;

public class PropertyCatalogFactory implements MessageCatalogFactory {
  static final String SELECTOR_BOILERPLATE_1 =
      "# Lines of the form key[form|form]=msg are for alternate forms of";
  static final String SELECTOR_BOILERPLATE_2 =
      "# the message according to Plural Count and Selector entries above.";
  static final String STRINGMAP_BOILERPLATE_1 =
      "# Lines of the form key[entry]=msg are for individual entries, and";
  static final String STRINGMAP_BOILERPLATE_2 =
      "# the line without [] lists the entries, separated by commas.";

  public PropertyCatalogFactory() {}

  public String getExtension() {
    return ".properties";
  }

  public Writer getWriter(Context context, String fileName) {
    PrintWriter pw = context.createTextFile(fileName, "UTF-8");
    if (pw == null) {
      return null;
    } else {
      PropertyCatalogFactory.PropertiesWriter writer =
          new PropertyCatalogFactory.PropertiesWriter(pw);
      return writer;
    }
  }

  private static class PropertiesWriter extends DefaultVisitor implements Writer {
    private final PrintWriter writer;
    private String baseKey;

    private static String stringJoin(String joiner, String... values) {
      StringBuilder buf = new StringBuilder();
      boolean needsJoiner = false;
      String[] var4 = values;
      int var5 = values.length;

      for (int var6 = 0; var6 < var5; ++var6) {
        String value = var4[var6];
        if (needsJoiner) {
          buf.append(joiner);
        } else {
          needsJoiner = true;
        }

        buf.append(value);
      }

      return buf.toString();
    }

    public PropertiesWriter(PrintWriter writer) {
      this.writer = writer;
    }

    public void close() throws IOException {
      this.writer.close();
    }

    public void endMessage(Message msg, MessageTranslation trans) {
      this.baseKey = null;
    }

    public MessageInterfaceVisitor visitClass() {
      return this;
    }

    public MessageVisitor visitMessage(Message msg, MessageTranslation trans) {
      this.writer.println();
      String description = msg.getDescription();
      if (description != null) {
        this.writer.println("# Description: " + description);
      }

      String meaning = msg.getMeaning();
      if (meaning != null) {
        this.writer.println("# Meaning: " + meaning);
      }

      List<Parameter> params = msg.getParameters();

      for (int i = 0; i < params.size(); ++i) {
        Parameter param = (Parameter) params.get(i);
        this.writer.print("#   " + i + " - " + param.getName());
        if (param.isAnnotationPresent(PluralCount.class)) {
          this.writer.print(", Plural Count");
        }

        if (param.isAnnotationPresent(Select.class)) {
          this.writer.print(", Selector");
        }

        if (param.isAnnotationPresent(Example.class)) {
          Example exampleAnnot = (Example) param.getAnnotation(Example.class);
          this.writer.print(", Example: " + exampleAnnot.value());
        }

        this.writer.println();
      }

      int[] selectorIndices = msg.getSelectorParameterIndices();
      if (selectorIndices.length > 0) {
        if (selectorIndices[0] >= 0) {
          this.writer.println("# Lines of the form key[form|form]=msg are for alternate forms of");
          this.writer.println(
              "# the message according to Plural Count and Selector entries above.");
        } else {
          this.writer.println("# Lines of the form key[entry]=msg are for individual entries, and");
          this.writer.println("# the line without [] lists the entries, separated by commas.");
        }
      }

      this.baseKey = this.quoteKey(msg.getKey());
      this.writer.println(
          this.baseKey
              + "="
              + this.propertiesMessage(msg.getMessageStyle(), msg.getDefaultMessage()));
      return this;
    }

    public void visitMessageInterface(MessageInterface msgIntf, GwtLocale sourceLocale) {
      this.writer.println("# Messages from " + msgIntf.getQualifiedName());
      this.writer.println("# Source locale " + sourceLocale);
    }

    public void visitTranslation(
        String[] formNames, boolean isDefault, MessageStyle style, String msg) {
      if (!isDefault) {
        if (msg == null) {
          msg = "";
        }

        String key = this.baseKey;
        key = key + "[" + stringJoin("|", formNames) + "]";
        this.writer.println(key + "=" + this.propertiesMessage(style, msg));
      }
    }

    private String propertiesMessage(MessageStyle style, String msg) {
      return msg == null ? "" : this.quoteValue(msg);
    }

    private String quoteKey(String str) {
      str = str.replace("\\", "\\\\");
      str = str.replace(" ", "\\ ");
      return this.quoteSpecial(str);
    }

    private String quoteSpecial(String str) {
      return str.replaceAll("([\f\t\n\r$!=:#])", "\\\\$1");
    }

    private String quoteValue(String str) {
      str = str.replace("\\", "\\\\");
      if (str.startsWith(" ")) {
        int n;
        for (n = 0; n < str.length() && str.charAt(n) == ' '; ++n) {}

        for (str = str.substring(n); n-- > 0; str = "\\ " + str) {}
      }

      return this.quoteSpecial(str);
    }
  }
}
