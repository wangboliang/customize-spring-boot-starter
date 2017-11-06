众所周知(不知道？[点此](http://www.jianshu.com/p/1a9fd8936bd8))，Spring Boot由众多Starter组成，随着版本的推移
<br>
Starter家族成员也与日俱增。在传统Maven项目中通常将一些层、组件拆分为
<br>
模块来管理，以便相互依赖复用，在Spring Boot项目中我们则可以创建自定义
<br>
Spring Boot Starter来达成该目的。
#### 好，开始，先创建一个Maven项目并引入依赖，pom.xml如下，供参考~ ####
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>example-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
    </dependencies>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <!-- Import dependency management from Spring Boot -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>1.5.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```
这里说下artifactId的命名问题，Spring 官方 Starter通常命名为spring-boot-starter-{name}如 spring-boot-starter-web， Spring官方建议非官方Starter命名应遵循{name}-spring-boot-starter的格式。
<br><br>
这里讲一下我们的Starter要实现的功能，很简单，提供一个Service，包含一个能够将字符串加上前后缀的方法String wrap(String word)。
```
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
前缀、后缀通过读取application.properties(yml)内的参数获得
```
@ConfigurationProperties("example.service")
public class ExampleServiceProperties {
    private String prefix;
    private String suffix;
    //省略 getter setter
```
#### 重点，编写AutoConfigure类 ####
```
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
解释下用到的几个和Starter相关的注解：
- @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
- @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
- @ConditionalOnProperty(prefix = "example.service",value = "enabled",havingValue = "true")，当配置文件中example.service.enabled=true时。<br>
更多相关注解，建议阅读[官方文档该部分](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/htmlsingle/#boot-features-bean-conditions)。
#### 最后一步，在resources/META-INF/下创建spring.factories文件，内容供参考下面~ ####
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.autocinfigure.ExampleAutoConfigure
```
OK，完事，运行 mvn:install打包安装，一个Spring Boot Starter便开发完成了。如果你需要该Starter的源代码，[点这里](https://github.com/lihengming/example-starter)。
_______________________________________
创建一个Spring Boot项目来 试试~
<br><br>
引入example-spring-boot-starter依赖
```
 <dependency>
    <groupId>com.example</groupId>
    <artifactId>example-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
 </dependency>
 ```
创建application.properties，进行配置
```
example.service.enabled=true
example.service.prefix=####
example.service.suffix=@@@@
```
创建一个简单的Spring Web Application，注入Starter提供的ExampleService看它能否正常工作~
```
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
- - -
总结下Starter的工作原理
1. Spring Boot在启动时扫描项目所依赖的JAR包，寻找包含spring.factories文件的JAR包
2. 根据spring.factories配置加载AutoConfigure类
3. 根据 @Conditional注解的条件，进行自动配置并将Bean注入Spring Context