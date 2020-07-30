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
 
 
### PRG Pattern (Post-Redirect-Get)