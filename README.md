# remodeling
测试平台的搭建

## API接口统一JSON格式返回
1. 定义API接口返回的JSON格式
2. 定义API接口状态码枚举值
3. 定义API返回体类

其实完成以上三步，就已经简单实现了统一JSON格式，但是有个问题，这种方式返回统一的JSON格式需要返回ApiResponse<Object>，但直接返回Object就可以了，避免重复劳动

### 全局处理（@RestControllerAdvice）
使用`@ResponseBody`注解会把返回Object序列化成JSON字符串,就先从这个入手吧, 大致就是在序列化前把Object赋值给`Result<Object>`就可以了，可以参考（`org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice和org.springframework.web.bind.annotation.ResponseBody`）
1. `@ResponseBody`继承类：创建注解接口`@ResponseApiBody`继承`@ResponseBody`，然后很干净，什么都没有
2. ResponseBodyAdvice继承类：创建ResponseApiBodyAdvice类实现`ResponseBodyAdvice<Object>`接口，并实现该接口方法

进行优化后，此时直接返回Object统一的JSON格式个，就不用每个返回都需要`ApiResponse<Object>`

但是，完成以上优化确实完成了接口统一JSON格式的返回，但是对于请求异常的情况并没有进行异常返回的统一处理

参考地址：https://github.com/galaxy-sea/galaxy-blogs/tree/master/code/responseResult

## knife4j的配置
knife4j是Swagger的升级版

官方网站：https://doc.xiaominfo.com/guide/useful.html#java开发

Maven pom.xml引入依赖
```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>${knife4j.version}</version>
</dependency>
```
创建配置类SwaggerConfiguration.java

## MyBatis-Plus使用

## 登录功能
安全认证框架：shiro

Apache shiro是一个强大且易用的Java安全框架，执行身份验证、授权、密码和会话管理

三个核心组件：Subject,SecurityManager和Realms