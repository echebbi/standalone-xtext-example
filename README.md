# Standalone xText example

This repository shows how to use [xText](https://www.eclipse.org/Xtext/index.html) in a standalone Java application.

In order to ease dependencies management, we'll use [Maven](https://maven.apache.org). However, the same process can be applied to a Gradle or a pure Java project.

> **Disclaimer**: the following explanations are based on xText 2.12.

## Table of Contents

* [1. Create the xText projects](#1-create-the-xtext-projects)
* [2. Generate xText artifacts](#2-generate-xtext-artifacts)
* [3. Build the grammar](#3-build-the-grammar)
* [4. Use xText in a standalone application](#4-use-xtext-in-a-standalone-application)

## 1. Create the xText projects

Within Eclipse, open the _New xText Project_ with `File > New > Other... > Xtext Project`

Fill the fields as you wish then click on `Next >`.

Select _Maven_ as _Preferred Build System_.

Click `Finish` to generate the different projects.

## 2. Generate xText artifacts

The next step is to launch the MWE2 workflow so that all Java classes required to parse a file are generated.

To this end, open the project containing the grammar, go into `src` and right-click on the `Generate*.mwe2` file and choose `Run As > MWE2 Workflow`.

## 3. Build the grammar

Right-click on the root project (`org.xtext.example.mydsl.parent`) and choose `Run As > Maven install`.

Maven then runs and package the different projects into JAR files.

## 4. Use xText in a standalone application

### Set up the dependencies

Create a new Maven project with the following dependencies:

```xml
<dependencies>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.10.0</version>
  </dependency>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.10.0</version>
  </dependency>
  <dependency>
    <groupId>org.eclipse.xtext</groupId>
    <artifactId>org.eclipse.xtext</artifactId>
    <version>2.12.0</version>
  </dependency>
</dependencies>
 ```

You must also add the JAR located under `org.xtext.example.mydsl/target` as a project's dependency.

### Parse a file

First of all, create a file respecting the xText grammar then move it to the root of the project.

Then, create a simple main as follows:

```java
public static void main(String[] args) {
	Injector injector = new MydslStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
	XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
	Resource resource = resourceSet.getResource(URI.createFileURI("./example.mydsl"), true);

	Model model = (Model) resource.getContents().get(0);
	model.getGreetings().forEach(System.out::println);
}
```

You can now execute the class to parse your grammar with a standalone Java application.
