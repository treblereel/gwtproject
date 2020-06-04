# GWT DOM Style Definitions

![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg) [![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![Chat on Gitter](https://badges.gitter.im/hal/elemento.svg)](https://gitter.im/gwtproject/gwt-modules) ![CI](https://github.com/gwtproject/gwt-dom-style-definitions/workflows/CI/badge.svg)

A collection of CSS styles to use with GWT 2 and J2CL, with no dependency on `gwt-user` (besides the Java Runtime Emulation).

## Dependency

1. Add the dependency to your build.

   For Maven:

   ```xml
   <dependency>
     <groupId>org.gwtproject.dom</groupId>
     <artifactId>gwt-dom-style-definitions</artifactId>
     <version>HEAD-SNAPSHOT</version>
   </dependency>
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.dom:gwt-dom-style-definitions:HEAD-SNAPSHOT")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.dom.style.Style" />
   ```

### Instructions
To build the module:

* run `mvn clean install`

on the parent directory.

**Note: To build the module you need Maven 3.6.3 or newer**

## System Requirements

**GWT DOM Style Definitions requires GWT 2.9.0 or greater!**


## Dependencies

GWT DOM Style Definitions does not depend on any other module.
