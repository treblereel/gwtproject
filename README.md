![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)

# gwt-window

A future-proof port of the `com.google.gwt.xml.XML` GWT module,
with no dependency on `gwt-user` (besides the Java Runtime Emulation),
to prepare for GWT 3 / J2Cl.

##  Migrating from `com.google.gwt.xml.XML`

1. Add the dependency to your build.

   For Maven:

   ```xml
   <dependency>
       <groupId>org.gwtproject.xml</groupId>
       <artifactId>gwt-xml</artifactId>
       <version>1.0-SNAPSHOT</version>
  </dependency>
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.xml:gwt-xml:1.0.0-RC-1")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.xml.XML" />
   ```

3. Change your `import`s in your Java source files:

   ```java
   import org.gwtproject.xml.client.*;
   ```

## Instructions

To build gwt-timer:

* run `mvn clean install`

on the parent directory.

To run the j2cl tests:

* switch to the 'gwt-xml-j2cl-tests' directory
* run `mvn j2cl:clean` & `mvn j2cl:test`


## System Requirements

**GWT Timer requires GWT 2.9.0 or newer!**
