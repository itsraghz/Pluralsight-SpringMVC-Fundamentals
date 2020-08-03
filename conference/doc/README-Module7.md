# Module 7 - Using Java Server Pages with Spring MVC View

## Changes being done to the codebase

 * I18N and Interceptors
 * PRG Pattern (Post-Redirect-Get)
 
### I18N and Interceptors

 * To make the text follow an Internationalization, we should make use of 
 a configuration as follows.
   * Use a file named `messages_<locale>.properties` where `en` goes for English and `es` goes for Spanish etc.,
   * The actual text being displayed in the JSP file will have the pattern `<spring:message code='first.name' text='default'>` 
   where the `first.name` is the key being configured in the properties/config file.
   
> Remember, we need to import a `taglib` from Spring Framework to use the `message` element.

 * In order to achieve this in the backend, we should use an `Interceptor` that does the job.
 * Did a few workarounds for the UTF-8 text to be displayed with Spring `messages.properties`.
    * Added a `web.xml` entry to have the `CharacerEncodingFilter` with a forcible encoding applied to `UTF-8` on all requests
    * Added a few methods in the `ConferenceConfig` class to override the `UTF-8` handling on all requests.
    * TBD - for a detailed steps to cover the R & D.
    * TBD - the UTF-8 input from JSP to Controller - not yet happening. 
    Only tried displaying it from the properties file to the JSP/HTML.    
 
### PRG Pattern (Post-Redirect-Get)

 * Famous *Double Submit* problem gets resolved by using the PRG pattern,
 where, upon successfully processing the POST request (non-idempotent) which 
 typically alters the System state, the URL gets changed to a GET request which
 will typically show the status of the request being processed 
 (Example: the status of the OrderId in an e-commerce application) rather than
 retaining the same URL for the POST request. This way, any accidental changes to the System 
 by means of the reloading of the page gets avoided. 
 * In order to achieve this, we need to change the return value from `registration`
 to `redirect:registration`, with which the Spring MVC automatically handles the *PRG* pattern.
 
```java
    @PostMapping(value="registration", produces = "text/html;charset=UTF-8")
    public String addRegistration(@ModelAttribute("registration") Registration registration) {
        System.out.println("RegistrationController - addRegistration() method invoked for : " + registration.getName());
        /*
         * registration - simple return of a POST request handling
         * redirect:registration - to implement the PRG (Post-Redirect-Get) pattern.
         *      Spring handles the stuff internally.
         */
        //return "registration";
        return "redirect:registration";
    }
```