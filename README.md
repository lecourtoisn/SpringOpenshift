# Spring boot and Netflix stack on OpenShift

## Running a Springboot application on Openshift
### Tomcat exclusion
Openshift provides a Wildfly base image for running java web applications which enters into conflict with tomcat which is embedded in Spring boot. So the following exclusion have to be set in the `pom.xml`:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### Servlet dependency
The Spring boot application must extends `org.springframework.boot.web.support.SpringBootServletInitializer`:
```
@Controller
@SpringBootApplication
public class SimpleController extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```

The following dependency must be set in the path:
```
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <scope>provided</scope>
</dependency>
```

### War packaging
The output of the build process must be a `.war` named `ROOT.war`.
To do so, add `<packaging>war</packaging>` in your `pom.xml` and `<finalName>ROOT</finalName>` in the build section.