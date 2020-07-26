# Pluralsight-SpringMVC-Fundamentals
Workspace of the Intelli IDEA for the SpringMVCFundamentals course by Bryan Hansen in PluralSight App.

## Steps

* Download the template from (start.spring.io)[http://start.spring.io] website with Spring Boot (latest stable - 2.3.2) with Spring WebMVC, JAR support on Java 11.
* Import the application in IntelliJ IDEA - as a Maven Project
* Run the ConferenceApplication.java - main() method
* The application runs on the embedded container
* Invoke the URL (http://localhost:8080)[http://localhost:8080] in the browser and see an Error Page
* Add a 'index.html' page under src/main/resources/static directory.
* Reload the context (App Server) and hit the same URL and you get a better output - non error and the contents of the index.html page

### To enable the Console Output in ANSI color

* Add this particular line in the `application.properties` file

```properties
spring.output.ansi.enabled=always
```

### Deploy it in the external Application Server - Tomcat

* Download the latest and stable version of *Apache Tomcat* (9.0.37) from (Apache Website) [http://tomcat.apache.org]
* Modify the `pom.xml` file to add a `<packaging>war</packaging>` element
* Add an another `dependency` in the `pom.xml` file as follows.

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<version>2.3.2.RELEASE</version>
			<scope>provided</scope>
		</dependency>
```
* Configure Apache Tomcat under the *Application Servers* under the File -> Settings -> Build, Tools and Deployment menu item.
* Configure a runtime for the Apache Tomcat 9.0.37 
  * Add a module - Conference
  * Modify the context root - from 'conference_war' to `conference`
  * Ensure the right JRE is set
* The *static* resources are *NOT* served by default in the external containers when served inside IntelliJ IDEA.
  * Add a new directory `src/main/webapp` and copy the *index.html* file from the 'static/resources' directory
* Click on the 'Tomcat 9.0.37' runtime configuration (the green icon)
* The Tomcat server runs, and the application module will be deployed 
* It automatically pulls up the browser and hits the url `http://localhost:8080/conference` automatically and serves the file content of `index.html`

### Add a Greeting

* Add a folder `/WEB-INF/jsp` under the same `webapp` directory
* Add a file called `greeting.jsp` inside the `/WEB-INF/jsp/` directory
    * Put a message `<h1>${message}</h1>` inside the `greeting.jsp` file
* Add a few configurations inside the `application.properties` file as follows.

```properties
# the trailing space is very important
spring.mvc.view.prefix=/WEB-INF/jsp/
# the "." is very important
spring.mvc.view.suffix=.jsp
```
* Make the Java class `ConferenceApplication` extends the `SpringBootServletInitializer` from the package `org.springframework.boot.web.servlet.support`
* Add a new class `GreetingController` inside the package `com.pluralsight.conference.controller`
    * The class should have an annotation `@Controller` (of stereotype) at the class level
* Add a new method named `greeting` (can be anything but recommended to keep it consistent with the functionality) as follows

```java
    @GetMapping("greeting")
    public String greeting(Map<String, Object> model) {
        model.put("message", "Hello Raghs");
        return "greeting";
    }
```
   * This method adds a key named `message` and puts a String into it.
   * The same key will be retrieved inside the `greeting.jsp` file
   * The return value of the method is the name of the view file without the extension `greeting` in `greeting.jsp`
    as the `prefix` and `suffix` are automatically deduced from the configuration made in the `application.properties` file.

* *Issue*: The main URL `http://localhost:8080/conference` seems to have broken, possibly due to the fact that the mapping has not been added explicitly.