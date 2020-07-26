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
* The Tomcat server runs and the application module is deployed 
* It automatically pulls up the browser and hits the url `http://localhost:8080/conference` automatically and serves the file content of `index.html`

