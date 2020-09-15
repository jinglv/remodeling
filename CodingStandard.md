# 代码规范

## 代码格式
代码格式遵循：阿里巴巴Java开发手册（泰山版）

### 强调以下两点
1.  类必须要有注释，类功能、注明开发者和日期
    ```
    // eg1:
    /**
     * Xxx Service 类
     *
     * @author xxx
     * @date 2019/11/21
     */
    public class XxxService {
    }
     
    // eg2:
    如果类没有类说明可以在@auther上边留一个空行
    /**
     *
     * @author xxx
     * @date 2019/11/21
     */
    public class XxxService {
    }
    ```
    IDEA里面可以创建相关的代码模板，只要新建类的时候IDEA会自动给加上
    
    IDEA模板配置 IntelliJ IDEA --> Preferences(快捷键：command + ,) --> Editor --> File and Code Templates --> Includes --> File Header
    ```
    /**
     *
     *
     * @author xxx
     * @date ${YEAR}/${MONTH}/${DAY}
     */
    ```
    作者统一用拼音全称，日期格式：yyyy/MM/dd
2. 代码格式化要求
    1. 源码文件必须为UTF-8编码
    2. 代码缩进使用4个空格
    3. 提交的代码必须要符合IDEA格式化规则（既可直接使用IDEA代码格式化功能）
    
    可使用插件Alibaba Java Coding Guidelines，对不规范的代码进行提示。[参考说明](https://www.cnblogs.com/bestzhang/p/util.html，https://www.bbsmax.com/A/lk5a4Xe0J1/)
    

对于代码格式化，可以在IDEA里面安装一个Save Actions插件，command+s的时候会自动进行格式化。插件安装完毕后做如下配置

![image-20200915140957575](https://gitee.com/JeanLv/study_image/raw/master///image-20200915140957575.png)

## 工具类库
- 优先使用JDK提供的Objects
- 其次使用Hutool [参考文档](https://www.hutool.cn/docs/#/)
- 若以上无法满足可以考虑使用Guava和apache相关工具类库

工具包：Lombok（用来消除Setter和Getter方法，以及引入日志静态变量log）

## 接口规范
1. ​​URL尽量符合RESTful API风格
​2. 错误码设计可以参考阿里Java开发手册，例如：​成功返回 "00000"
3. ​接口文档规范采用Swagger UI规范
4. 接口返回数据格式约定
```
// code = '00000' 表示业务正常
// message 业务描述信息
// data 业务数据，当没有业务数据时，data不返回
 
{
    "code": "00000",
    "message": "ok",
    "data": {}
}
```

## Git commit日志规范
commit日志结构规范
```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

type代表某次提交的类型，比如是修复一个bug还是增加一个新的feature。

所有的type类型如下：

|type|说明|
|---|---|
|feat|新增feature|
|perf|优化相关，比如提升性能、体验|
|refactor|代码重构，没有加新功能或者修复bug|
|fix|修复bug|
|test|测试用例，包括单元测试、集成测试等|
|style|仅仅修改了空格、格式缩进、逗号等等，不改变代码逻辑|
|chore|改变构建流程、或者增加依赖库、工具、数据库脚本等|
|docs|仅仅修改了文档，比如README, CHANGELOG, CONTRIBUTE等等|
|revert|回滚到上一个版本|

格式要求：
```
#
# 主体内容：更详细的说明文本，建议72个字符以内。 需要描述的信息包括:
#
# * 为什么这个变更是必须的? 它可能是用来修复一个bug，增加一个feature，提升性能、可靠性、稳定性等等
# * 他如何解决这个问题? 具体描述解决问题的步骤
# * 是否存在副作用、风险?
#
# 尾部：如果需要的化可以添加一个链接到issue地址或者其它文档，或者关闭某个issue。
```

示例一：
```
feat: 新增分页获取用户信息接口
```

示例二：
```
feat: 新增分页获取用户信息接口
 
1、分页获取用户列表接口
2、新增用户信息接口
```

示例三：
```
feat: 新增用户管理页面
 
1、用户列表页面
2、用户新增弹窗
3、用户修改弹窗
 
前端页面开发文档：http://xxx.xxx.xxx
```
注意：当某次提交的内容较多时，应当将提交内容进行一下分类，然后分多次进行提交。