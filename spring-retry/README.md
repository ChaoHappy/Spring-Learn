# spring-retry
# 1. 概述
Spring Retry提供了自动重新调用失败操作的能力。这对于错误可能是暂时性的（如暂时性的网络故障）很有帮助。Spring Retry提供了对流程和基于策略的行为的声明性控制，易于扩展和定制。

# 2. Maven依赖
首先，将依赖项添加到pom.xml中：
 ```xml
<properties>
        <spring.version>4.3.25.RELEASE</spring.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>

        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
 ```

# 3. 启动Spring Retry （注解方式）
要在应用程序中启用Spring Retry，我们需要将@enablerery注释添加到实现类上：
```java
@EnableRetry
@Service
public class MyServiceImpl implements MyService{

    public void retryService(String sql) throws SQLException {
        System.out.println("retryService");
        throw new SQLException();
    }

    public void recover(SQLException e, String sql) {
        System.out.println("recover");
    }
}
```
 # 4. 使用Retry注解
 我们可以使用注释进行方法调用，以便在失败时重试。 
 
 ## 4.1 @Retryable
 
 要向方法添加重试功能，可以使用@Retryable： 
 
 ```java
 @Service
 public interface MyService {
     @Retryable(
       value = { SQLException.class }, 
       maxAttempts = 2,
       backoff = @Backoff(delay = 5000))
     void retryService(String sql) throws SQLException;
     ...
 }
 ```
这里，重试行为是使用@Retryable的属性定制的。在本例中，仅当方法引发SQLException时，才会尝试重试。最多可重试2次，延迟5000毫秒。 

如果使用@Retryable时没有任何属性，如果方法因异常而失败，则将尝试最多三次重试，延迟一秒。 

## 4.2 @Recover

@Recover注释用于在@Retryable方法因指定的异常而失败时定义单独的恢复方法： 

```java
@Service
public interface MyService {
    ...
    @Recover
    void recover(SQLException e, String sql);
}
```

因此，如果retryService（）方法抛出一个SQLException，将调用recover（）方法。合适的恢复处理程序的第一个参数类型为Throwable（可选）。从失败方法的参数列表中按与失败方法相同的顺序填充后续参数，并使用相同的返回类型。 
 

 
 
 
 
 
 
 
 
 
 
 