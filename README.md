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

## Module 5 - Creating Controllers in Spring MVC

Adding a Registration Controller

* Add a new jsp filed `/WEB-INF/jsp/registration.jsp` and just add a `<h1>Registration</h1>` element in the body of the JSP file
* Add a new `Controller` - `com.pluralsight.conference.controller.RegistrationController` with the same method syntax as that of `GreetingController` with the `@GetMapping("registration")`.
* Add a new Java class `com.pluralsight.conference.model.Registration` with a single property `name` and its getters and setters
* Delete the `webapp/index.html` file as the Spring MVC is all set up now
* Modify the `src/main/resources/static/index.html` file with the links to `greeting` and `registration`.
```jsp
<body>
    <h1>Hello</h1>
    <a href="greeting">Greeting</a>
    <a href="registration">Registration</a>
</body>
```    
* Comment the method with `@GetMapping("registration")` in the `RegistrationController.java` and add the two new methods as follows.
```java
    @GetMapping("registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration) {
        System.out.println("RegistrationController - register() method invoked");
        return "registration";
    }

    @PostMapping("registration")
    public String addRegistration(@ModelAttribute("registration") Registration registration) {
        System.out.println("RegistrationController - addRegistration() method invoked for : " + registration.getName());
        return "registration";
    }
```
* The first one was to just have the `HTTP Get` mapping and the 2nd one was to have the `HTTP Post` mapping, which happens via Form Submit.
* Add the `spring:form` taglib to the `registration.jsp` as follows
```jsp
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
```

> Note: You need to enable the `JSP Support` in the Intellij IDEA to have the proper auto-completion and context-sensitive help.

* Add a `<form:form>` element in the `registration.jsp` with a HTML table to have an `form:input` to capture/map the `path` element with the `name`
* Add a `modelAttribute` attribute in the `<form:form>` to map the elements in the HTML from to this Model attribute. 

> Remember: The same attribute name `registration` has been captured in the Controller methods with `@GetMapping` and `@PostMapping` annotations.
*  Rerun the Tomcat Server and click the URL `http://localhost:8080/conference/registratation`
   * You will have a HTML form to enter a name
   * Click on the 'Add Registration' button
   * The dispatcherServlet is invoked and delegates the request to the `RegistrationController` class 
   * The attribute is bound to the model `Registration` 
   * The Controller method `addRegistration` - the one with `@PostMapping` annotation is invoked
   * You can see the `System.out.println()` statement with the message in the Console that prints out the name entered in the HTMLL form - via `regitration.getName()` method
   * The `ViewResolver` comes into picture and with the help of the configuration values in the `application.properties` it picks up the jsp file `registration.jsp' with the help of the return value of the `addRegisgration()` method which is - "registration"
   
   