# GWT JSONP


**Needs to be rewritten**

![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)  [![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![Chat on Gitter](https://badges.gitter.im/hal/elemento.svg)](https://gitter.im/gwtproject/gwt-modules) ![CI](https://github.com/gwtproject/gwt-jsonp/workflows/CI/badge.svg)

A future-proof port of the `com.google.gwt.jsonp.JsonP` GWT module, with no dependency on `gwt-user` (besides the Java Runtime Emulation), to prepare for GWT 3 / J2Cl.

##  Migrating from `com.google.gwt.jsonp.JsonP`

1. Add the dependency to your build.

   For Maven:

   ```xml
   <dependency>
     <groupId>org.gwtproject.jsonp</groupId>
     <artifactId>gwt-jsonp</artifactId>
     <version>HEAD-SNAPSHOT</version>
   </dependency>
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.jsonp:gwt-jsonp:HEAD-SNAPSHOT")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.jsonp.Jsonp" />
   ```

3. Change the `import`s in your Java source files:

   ```java
   import org.gwtproject.jsonp.client.JsonpGlobal;
   ```

## Instructions

To build gwt-jsonp:

* run `mvn clean verify`

on the parent directory. This will build the artifact and run tests against the JVM, J2CL, and GWT2.

## System Requirements

**GWT JSONP requires GWT 2.9.0 or newer!**

## Dependencies

* [Elemental 2](https://github.com/google/elemental2)
* [GWT Callback](https://github.com/gwtproject/gwt-callback)
* [GWT Safe-HTML](https://github.com/gwtproject/gwt-safehtml)
* [GWT Timer](https://github.com/gwtproject/gwt-timer)
