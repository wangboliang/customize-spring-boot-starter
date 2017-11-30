Starters是一个依赖描述符的集合，你可以将它包含进项目中，这样添加依赖就非常方便。你可以获取所有Spring及相关技术的一站式服务，而不需要翻阅示例代码，拷贝粘贴大量的依赖描述符。例如，如果你想使用Spring和JPA进行数据库访问，只需要在项目中包含spring-boot-starter-data-jpa依赖，然后你就可以开始了。
<br><br>
名字有什么含义：所有官方starters遵循相似的命名模式：spring-boot-starter-\*，在这里*是一种特殊的应用程序类型。该命名结构旨在帮你找到需要的starter。很多集成于IDEs中的Maven插件允许你通过名称name搜索依赖。例如，使用相应的Eclipse或STS插件，你可以简单地在POM编辑器中点击ctrl-space，然后输入"spring-boot-starter"就可以获取一个完整列表。
第三方starters不应该以spring-boot开头，因为它跟Spring Boot官方artifacts冲突。一个acme的第三方starter通常命名为acme-spring-boot-starter。
<br><br>
以下应用程序starters是Spring Boot在org.springframework.boot group下提供的：
### Spring Boot应用程序starters
Name | Description | Pom
---- | ---- | ----
spring-boot-starter | 核心starter，包括自动配置支持，日志和YAML | [Pom](https://github.com/spring-projects/spring-boot/blob/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter/pom.xml)
spring-boot-starter-activemq | 用于使用Apache ActiveMQ实现JMS消息 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-activemq/pom.xml)
spring-boot-starter-amqp | 用于使用Spring AMQP和Rabbit MQ | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-amqp/pom.xml)
spring-boot-starter-aop | 用于使用Spring AOP和AspectJ实现面向切面编程 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-aop/pom.xml)
spring-boot-starter-artemis | 使用Apache Artemis实现JMS消息	 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-artemis/pom.xml)
spring-boot-starter-batch | 对Spring Batch的支持 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-batch/pom.xml)
spring-boot-starter-cache |	用于使用Spring框架的缓存支持 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-cache/pom.xml)
spring-boot-starter-cloud-connectors | 对Spring Cloud Connectors的支持，用于简化云平台下（例如Cloud Foundry 和Heroku）服务的连接 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-cloud-connectors/pom.xml)
spring-boot-starter-data-cassandra | 用于使用分布式数据库Cassandra和Spring Data Cassandra | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-cassandra/pom.xml)
spring-boot-starter-data-couchbase | 用于使用基于文档的数据库Couchbase和Spring Data Couchbase | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-couchbase/pom.xml)
spring-boot-starter-data-elasticsearch | 用于使用Elasticsearch搜索，分析引擎和Spring Data Elasticsearch | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-elasticsearch/pom.xml)
spring-boot-starter-data-gemfire | 用于使用分布式数据存储GemFire和Spring Data GemFire | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-gemfire/pom.xml)
spring-boot-starter-data-jpa | 用于使用Hibernate实现Spring Data JPA | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-jpa/pom.xml)
spring-boot-starter-data-ldap | 使用Spring Data LDAP的启动器 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-ldap/pom.xml)
spring-boot-starter-data-mongodb | 用于使用基于文档的数据库MongoDB和Spring Data MongoDB	 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-mongodb/pom.xml)
spring-boot-starter-data-neo4j | 用于使用图数据库Neo4j和Spring Data Neo4j | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-neo4j/pom.xml)
spring-boot-starter-data-redis | 用于使用Spring Data Redis和Jedis客户端操作键—值数据存储Redis | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-redis/pom.xml)
spring-boot-starter-data-rest | 用于使用Spring Data REST暴露基于REST的Spring Data仓库 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-rest/pom.xml)
spring-boot-starter-data-solr | 通过Spring Data Solr使用Apache Solr搜索平台 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-data-solr/pom.xml)
spring-boot-starter-freemarker | 用于使用FreeMarker模板引擎构建MVC web应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-freemarker/pom.xml)
spring-boot-starter-groovy-templates | 用于使用Groovy模板引擎构建MVC web应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-groovy-templates/pom.xml)
spring-boot-starter-hateoas | 用于使用Spring MVC和Spring HATEOAS实现基于超媒体的RESTful web应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-hateoas/pom.xml)
spring-boot-starter-integration | 用于使用Spring Integration | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-integration/pom.xml)
spring-boot-starter-jdbc | 对JDBC的支持（使用Tomcat JDBC连接池） | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-jdbc/pom.xml)
spring-boot-starter-jersey | 用于使用JAX-RS和Jersey构建RESTful web应用，可使用spring-boot-starter-web替代 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-jersey/pom.xml)
spring-boot-starter-jooq | 用于使用JOOQ访问SQL数据库，可使用spring-boot-starter-data-jpa或spring-boot-starter-jdbc替代 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-jooq/pom.xml)
spring-boot-starter-jta-atomikos | 用于使用Atomikos实现JTA事务 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-jta-atomikos/pom.xml)
spring-boot-starter-jta-bitronix |	用于使用Bitronix实现JTA事务 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-jta-bitronix/pom.xml)
spring-boot-starter-jta-narayana | Spring Boot Narayana JTA Starter | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-jta-narayana/pom.xml)
spring-boot-starter-mail | 用于使用Java Mail和Spring框架email发送支持 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-mail/pom.xml)
spring-boot-starter-mobile | 用于使用Spring Mobile开发web应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-mobile/pom.xml)
spring-boot-starter-mustache | 用于使用Mustache模板引擎构建MVC web应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-mustache/pom.xml)
spring-boot-starter-security | 对Spring Security的支持 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-security/pom.xml)
spring-boot-starter-social-facebook | 用于使用Spring Social Facebook | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-social-facebook/pom.xml)
spring-boot-starter-social-linkedin | 用于使用Spring Social LinkedIn | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-social-linkedin/pom.xml)
spring-boot-starter-social-twitter | 对使用Spring Social Twitter的支持 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-social-twitter/pom.xml)
spring-boot-starter-test | 用于测试Spring Boot应用，支持常用测试类库，包括JUnit, Hamcrest和Mockito | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-test/pom.xml)
spring-boot-starter-thymeleaf | 用于使用Thymeleaf模板引擎构建MVC web应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-thymeleaf/pom.xml)
spring-boot-starter-validation | 用于使用Hibernate Validator实现Java Bean校验 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-validation/pom.xml)
spring-boot-starter-web | 用于使用Spring MVC构建web应用，包括RESTful。Tomcat是默认的内嵌容器 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-web/pom.xml)
spring-boot-starter-web-services | 对Spring Web服务的支持 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-web-services/pom.xml)
spring-boot-starter-websocket | 用于使用Spring框架的WebSocket支持构建WebSocket应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-websocket/pom.xml)

### Spring Boot生产级starters
Name | Description | Pom
---- | ---- | ----
spring-boot-starter-actuator | 用于使用Spring Boot的Actuator，它提供了production ready功能来帮助你监控和管理应用程序 | [Pom](https://github.com/spring-projects/spring-boot/blob/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-actuator/pom.xml)
spring-boot-starter-remote-shell |	用于通过SSH，使用CRaSH远程shell监控，管理你的应用 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-remote-shell/pom.xml)

### Spring Boot技术性starters
Name | Description | Pom
---- | ---- | ----
spring-boot-starter-undertow | 用于使用Undertow作为内嵌servlet容器，可使用spring-boot-starter-tomcat替代 | [Pom](https://github.com/spring-projects/spring-boot/blob/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-undertow/pom.xml)
spring-boot-starter-jetty |	用于使用Jetty作为内嵌servlet容器，可使用spring-boot-starter-tomcat替代 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-jetty/pom.xml)
spring-boot-starter-logging | 用于使用Logback记录日志，默认的日志starter | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-logging/pom.xml)
spring-boot-starter-tomcat | 用于使用Tomcat作为内嵌servlet容器，spring-boot-starter-web使用的默认servlet容器 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-tomcat/pom.xml)
spring-boot-starter-log4j2 | 用于使用Log4j2记录日志，可使用spring-boot-starter-logging代替 | [Pom](https://github.com/spring-projects/spring-boot/tree/v1.5.4.RELEASE/spring-boot-starters/spring-boot-starter-log4j2/pom.xml)

### 自定义starters
这里就以example-spring-boot-starter为例
#### 引入pom依赖
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.wangboliang</groupId>
    <artifactId>example-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>example-spring-boot-starter</name>
    <description>Example stater for Spring Boot</description>

    <parent>
        <groupId>com.wangboliang</groupId>
        <artifactId>customize-spring-boot-starters</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
            <version>1.5.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <version>1.5.4.RELEASE</version>
        </dependency>
    </dependencies>

</project>
```

#### 核心配置类AutoConfigure
构建starter的关键是编写一个装配类，这个类可以提供该starter核心bean。这里我们的starter提供一个能够将字符串加上前后缀的方法，我们叫它为ExampleService。
负责对这个bean进行自动化装配的类叫做ExampleAutoConfigure。保存application.properties配置信息的类叫做ExampleServiceProperties。这三种类像是铁三角一样，你可以在很多的spring-boot-starter中看到他们的身影。
<br><br>
我们首先来看ExampleAutoConfigure的定义。
```java
@Configuration
@ConditionalOnClass(ExampleService.class)
@EnableConfigurationProperties(ExampleServiceProperties.class)
public class ExampleAutoConfigure {

    @Autowired
    private ExampleServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "example.service",value = "enabled",havingValue = "true")
    ExampleService exampleService (){
        return  new ExampleService(properties.getPrefix(),properties.getSuffix());
    }

}
```
我们首先讲一下源码中注解的作用。
- ``@Configuration``,被该注解注释的类会提供一个或多个@bean修饰的方法，并且会被spring容器处理来生成bean definitions。
- ``@bean``注解是必须修饰函数的，该函数可以提供一个bean。而且该函数的函数名必须和bean的名称一致，除了首字母不需要大写。
- ``@ConditionalOnClass``注解是条件判断的注解，表示对应的类在classpath目录下存在时，才会去解析对应的配置文件。
- ``@EnableConfigurationProperties``注解给出了该配置类所需要的配置信息类，也就是ExampleServiceProperties类，这样spring容器才会去读取配置信息到ExampleServiceProperties对象中。
- ``@ConditionalOnMissingBean``注解也是条件判断的注解，表示如果不存在对应的bean条件才成立，这里就表示如果已经有ExampleService的bean了，那么就不再进行该bean的生成。这个注解十分重要，涉及到默认配置和用户自定义配置的原理。也就是说用户可以自定义一个ExampleService的bean,这样的话，spring容器就不需要再初始化这个默认的bean了。
- ``ConditionalOnProperty``注解是条件判断的注解，表示如果配置文件中的响应配置项数值为true,才会对该bean进行初始化。

``@ConditionalOnProperty(prefix = "example.service",value = "enabled",havingValue = "true")``，表示配置文件中``example.service.enabled=true``时。
<br><br>
更多相关注解，建议阅读[官方文档该部分](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/htmlsingle/#boot-features-bean-conditions)。
<br><br>
到这里，大概都明白了ExampleAutoConfigure的作用了吧，spring容器会读取相应的配置信息到ExampleServiceProperties中，然后依据条件判断初始化ExampleService这个bean。集成了该starter的项目就可以直接使用ExampleService了。

#### 配置信息类Properties
存储配置信息的类ExampleServiceProperties很简单，源码如下所示:
```java
@ConfigurationProperties("example.service")
public class ExampleServiceProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
```
``@ConfigurationProperties``注解就是让spring容器知道该配置类的配置项前缀是什么，上述的源码给出的配置信息项有``example.service.enabled=true,example.service.prefix=####和example.service.suffix=@@@@``。
这些配置信息都会由spring容器从application.properties文件中读取出来设置到该类中

#### starter提供功能的Service
ExampleService类是提供整个starter的核心功能的类
```java
public class ExampleService {

    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }

}
```

#### 注解配置和spring.factories
自定义的starter有两种方式来通知spring容器导入自己的auto-configuration类。
<br><br>
- 一般都是在starter项目的resources/META-INF文件夹下的spring.factories文件中加入需要自动化配置类的全限定名称。
```java
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.spring.boot.example.autoconfigure.ExampleAutoConfigure
```
spring boot项目中的``EnableAutoConfigurationImportSelector``会自动去每个jar的相应文件下查看spring.factories文件内容，并将其中的类加载出来在auto-configuration过程中
进行配置。而``EnableAutoConfigurationImportSelector``在``@EnableAutoConfiguration``注解中被import。
<br><br>
- 第一种方法只要是引入该starter，那么spring.factories中的auto-configuration类就会被装载，但是如果你希望有更加灵活的方式，那么就使用自定义注解来引入装配类。
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ExampleAutoConfigure.class)
@Documented
public @interface EnableExample {
}
```
有了这个注解，你可以在你引入该starter的项目中使用该注解，通过``@import``注解，spring容器会自动加载ExampleAutoConfigure并自动化进行配置。
<br><br>
OK，完事，运行 mvn:install打包安装，一个Spring Boot Starter便开发完成了。如果你需要该Starter的源代码，[点这里](https://github.com/wangboliang/customize-spring-boot-starter/tree/master/example-spring-boot-starter)。
___
创建一个Spring Boot项目来 试试~
<br><br>
引入example-spring-boot-starter依赖
```xml
 <dependency>
    <groupId>com.example</groupId>
    <artifactId>example-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
 </dependency>
 ```
 创建application.properties，进行配置
 ```properties
 example.service.enabled=true
 example.service.prefix=####
 example.service.suffix=@@@@
 ```
 创建一个简单的Spring Web Application，注入Starter提供的ExampleService看它能否正常工作~
 ```java
 @SpringBootApplication
 @RestController
 public class Application {

     public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
     }

     @Autowired
     private ExampleService exampleService;

     @GetMapping("/input")
     public String input(String word){
         return exampleService.wrap(word);
     }

 }
 ```
 启动Application，访问/input接口试试看~