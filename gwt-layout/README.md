# GWT Layout

![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)  [![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![Chat on Gitter](https://badges.gitter.im/hal/elemento.svg)](https://gitter.im/gwtproject/gwt-modules) ![CI](https://github.com/gwtproject/gwt-layout/workflows/CI/badge.svg)

A future-proof port of the `com.google.gwt.layout.Layout` GWT module, with no dependency on `gwt-user` (besides the Java Runtime Emulation), to prepare for GWT 3 / J2Cl.

##  Migrating from `com.google.gwt.layout.Layout`

1. Add the dependency to your build.

   For Maven:

   ```xml
   <dependency>
       <groupId>org.gwtproject.layout</groupId>
       <artifactId>gwt-layout</artifactId>
       <version>HEAD-SNAPSHOT</version>
   </dependency>
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.layout:gwt-layout:HEAD-SNAPSHOT")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.layout.Layout" />
   ```

3. Change the `import`s in your Java source files:

   ```java
   import org.gwtproject.layout.client.*;
   ```

## Instructions

To build gwt-layout:

* run `mvn clean verify`

on the parent directory. This will build the artifact and run tests against the JVM, J2CL, and GWT2.

## System Requirements

**GWT Layout requires GWT 2.9.0 or newer!**

## Dependencies

GWT Layout depends on

* gwt-animation
* gwt-aria
* gwt-dom
* gwt-dom-style-definition
