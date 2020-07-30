# Module 6 - Creating Views in Spring MVC Applications

## Changes being done to the codebase

### Added a new Configuration Class for  View Resolver

 * Added a new *Configuration* class named `ConferenceConfig` (with the _Annotation_ `@Configuration`)
 * Added a new method that returns a `@Bean` and an actual object of type `InternalResourceViewResolver` with the 
   following syntax.
   
   ```java
   @Configuration
   public class ConferenceConfig {
   
       @Bean
       public ViewResolver getViewResolver() {
           System.out.println("ConferenceConfig - getViewResolver() called..");
           InternalResourceViewResolver bean = new InternalResourceViewResolver();
           bean.setPrefix("/WEB-INF/jsp/");
           bean.setSuffix(".jsp");
           bean.setOrder(0);
           return bean;
       }
   }
   ```
   * Commented out the following line the `application.properties` file, as they have been 
   now configured in the `ConferenceConfig` class.
   
   ```properties
   ## Web MVC - View components
   # These properties are configured now in the ConferenceConig.java class via InternalResourceViewResolver
   #spring.mvc.view.prefix=/WEB-INF/jsp/
   #spring.mvc.view.suffix=.jsp
   ```
   
   * Rebuild the project and execute the Tomcat instance
   * The application should work the same way what it was earlier. We have now overridden the 
   Configuration via a Custom class with the `@Configuration` Annotation, and a method that returns the
   appropriate `ViewResolver` implementation that is expected by the Spring MVC framework.
   
  ### Verification 
  
   * You can verify the Console logs for the proof that this configuration class is being called and the 
   ViewResolver is being set up - when you invoke the .jsp page for the first time.
   
   * Console Output :
   
   ```text
     .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::        (v2.3.2.RELEASE)
    
    2020-07-30 23:39:37.363  INFO 10300 --- [on(2)-127.0.0.1] c.p.conference.ConferenceApplication     : Starting ConferenceApplication v0.0.1-SNAPSHOT on LenovaG51-35 with PID 10300 (C:\installedSoft\apache-tomcat-9.0.37\webapps\conference\WEB-INF\classes started by admin in C:\installedSoft\apache-tomcat-9.0.37\bin)
    2020-07-30 23:39:37.414  INFO 10300 --- [on(2)-127.0.0.1] c.p.conference.ConferenceApplication     : No active profile set, falling back to default profiles: default
    2020-07-30 23:39:43.368  INFO 10300 --- [on(2)-127.0.0.1] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 5661 ms
    ConferenceConfig - getViewResolver() called..
    2020-07-30 23:39:48.132  INFO 10300 --- [on(2)-127.0.0.1] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
    2020-07-30 23:39:49.336  INFO 10300 --- [on(2)-127.0.0.1] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]
    2020-07-30 23:39:49.957  INFO 10300 --- [on(2)-127.0.0.1] c.p.conference.ConferenceApplication     : Started ConferenceApplication in 17.71 seconds (JVM running for 44.198)
    [2020-07-30 11:39:50,159] Artifact conference:war: Artifact is deployed successfully
    [2020-07-30 11:39:50,161] Artifact conference:war: Deploy took 36,445 milliseconds
    2020-07-30 23:39:52.546  INFO 10300 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
    2020-07-30 23:39:52.615  INFO 10300 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 69 ms
   ```
  * The rest of the output be as usual. 
  
  ```text
    GreetingController - greeting() method invoked
    RegistrationController - register() method invoked
    RegistrationController - addRegistration() method invoked for : M Raghavan 
  ```
  > Remember that, this ViewResolver being a framework class, this *Configuration* setup will be invoked only once at the very first invocation.

### ViewResolvers

 *  Spring provides various `ViewResolvers` which you can use it in your application, or even you can
 create your own custom View Resolver, as they are all going to the implementation of the `ViewResolver` interface. 

## Add a ResourceHandler

 * We can add a `ResourceHandler` for handling the static resources like `.pdf`, `.jpg` or the cached contents etc.,  
 via the Spring MVC application.
 * Make the `ConfigConference` class *implement* the `WebMvcConfigurer` interface
 * Add an implementation to the method as follows, which is an overridden method of the implemented interface.
 
 ```java
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("ConferenceConfig - addResourceHandlers() invoked.");
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("/WEB-INF/pdf/");
    } 
 ```
 * Create a new folder called `pdf` inside the directory `./src/webapp/WEB-INF/` 
 * Add a new PDF file - which is exported from the same `ConferenceConfig.java` class - as `ConferenceConfig.pdf`.
  
### Verification

 * Hit the URL : (http://localhost:8080/conference/files/ConferenceConfig.pdf) [http://localhost:8080/conference/files/ConferenceConfig.pdf]
 * The browser will render the PDF file - as the Spring MVC has looked for a file inside the `/WEB-INF/pdf/` for any resoruce
 being asked for with the mapping `/files/`.