# GWT Callback

![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)  [![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![Chat on Gitter](https://badges.gitter.im/hal/elemento.svg)](https://gitter.im/gwtproject/gwt-modules) ![CI](https://github.com/gwtproject/gwt-callback/workflows/CI/badge.svg)


A future-proof port of the `com.google.gwt.user.client.rpc` GWT module, with no dependency on `gwt-user` (besides the Java Runtime Emulation), to prepare for GWT 3 / J2Cl.

##  Migrating from `com.google.gwt.user.client.AsyncCallback`

1. Add the dependency to your build.

   For Maven:

   ```xml
   <dependency>
     <groupId>org.gwtproject.callback</groupId>
     <artifactId>gwt-callback</artifactId>
     <version>HEAD-SNAPSHOT</version>
   </dependency>
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.callback:gwt-callback:HEAD-SNAPSHOT")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.callback.AsyncCallback" />
   ```

3. Change the `import`s in your Java source files:

   ```java
   import org.gwtproject.callback.client;
   ```

### Instructions
To build the module:

* run `mvn clean install`

on the parent directory.

**Note: To build the module you need Maven 3.6.3 or newer**

## System Requirements

**GWT Callback requires GWT 2.9.0 or greater!**


## Dependencies

GWT Callback does not depend on any other module.
