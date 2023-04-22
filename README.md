# 项目背景

## 前景


在线教育前景广阔，因为它突破地域限制、提供个性化学习路径，弥补资源不均；在线教育项目可提高教育质量、降低成本，助力知识普及与教育公平。

## 模块

<div>
	<center>
		<img 
			src="https://p.ipic.vip/5jbsgv.png"
			style="zoom:50%"
		/><br>
		项目模块
	</center>
</div>

## 技术点

* 后端：Spring Boot、Spring Cloud、Mybatis-Plus、Spring Security、Redis、Maven、EasyExcel、JWT、OAuth2
* 前端：HTML5、CSS、Vue.js、Element-ui、Axios、Node.js
* 其他：阿里云oss、阿里云视频点播服务、阿里云短信服务、微信登录和支付、Git、Docker、Jenkins

### MyBatis-Plus

MyBatis-Plus（ 简称 MP）是一个MyBatis 的增强工具，在MyBatis 的基础上只做增强不做政变，为简化开发、提高开发效率

1. `UserMapper`接口继承了继承了`BaseMapper`
2. 在`UserMapper`类上添加注解`@Repository`让其注入例子


#### 主键策略

[分布式系统唯一ID生成方案汇总](https://www.cnblogs.com/haoxinyue/p/5208136.html)

#### 自动填充

1. 在实体类对应的属性上添加注解`@TableFiele()`
2. 创建类，实现接口`Meta0bjectHandler`，实现现接口里面的方法
3. 通过`Meta0bjectHandler`里面的方法来实现自动填充

#### 乐观锁

**解释：**并行处理事务，其中会使用到一个字段`Version=初始值`，如果有A和B同时操作一个表，当A操作完成，会检查`Version`是不是等于初始值，如果是，提交事务，`Version+1`。当B操作完成要提交时，同样检查`Version`是不是等于初始值，此时发现`Version=初始值+1`不等于初始值，那么无法提交。



1. 在配置类中创建`OptimisticLockerInterceptor`并通过`@Bean`自动注入依赖，修改被`@Version`注解的变量时会自动增加版本号，并启用乐观锁

与之对应的悲观锁，串行操作：一次只能同时进行最多一个进程

#### 分页查询

1. 先在配置类中加入分页查询插件`PaginationInterceptor`

2. 在分页的Mapper函数中使用分页查询操作

   ~~~java
   //分页查询
       @Test
       public void testPage() {
           //1 创建page对象
           //传入两个参数：当前页 和 每页显示记录数
           Page<User> page = new Page<>(1,3);
           //调用mp分页查询的方法
           //调用mp分页查询过程中，底层封装
           //把分页所有数据封装到page对象里面
           userMapper.selectPage(page,null);
   
           //通过page对象获取分页数据
           System.out.println(page.getCurrent());//当前页
           System.out.println(page.getRecords());//每页数据list集合
           System.out.println(page.getSize());//每页显示记录数
           System.out.println(page.getTotal()); //总记录数
           System.out.println(page.getPages()); //总页数
   
           System.out.println(page.hasNext()); //下一页
           System.out.println(page.hasPrevious()); //上一页
   
       }
   ~~~
   
   
#### 删除操作

1. **物理删除：**直接用Mapper的`deleteById`等方法
2. **逻辑删除：**在配置类中添加`ISqlInjector`插件，**物理删除**的作用即被转化

~~~java
//    逻辑删除插件
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }
~~~

#### 性能分析

1. 三种环境，`@Profile({"dev”,,"test"})`

   - ﻿﻿dev: 开发环境

   - ﻿﻿test：测试环境

   - ﻿﻿prod：生产环境

springboot配置：`spring.profiles.active=dev `

#### 条件查询

有多种查询条件，通过下面代码(`wrapper`)来实现：

~~~java
void selectTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询age>=30记录
        //第一个参数字段名称，第二个参数设置值
        wrapper.ge("age",30);
        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }
~~~

#### 代码生成器

可以添加在`Test`包里，直接运行即可自动生成

~~~java
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author
 * @since 2018/12/13
 */
public class CodeGenerator {

    @Test
    public void run() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("/Users/xieburou/Documents/java/谷粒学苑/guli_parent/service/service_edu" + "/src/main/java");
        gc.setAuthor("testjava");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setIdType(IdType.ID_WORKER); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/guli");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Batmansprt0");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice"); //模块名
        pc.setParent("edu.gdut");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu_teacher");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
~~~

自动生成系列类

<div>
	<center>
  	<img
         src="https://p.ipic.vip/2ioqfk.png"
         style="zoom:100%"
         /><br>
    代码生成器自动生成
  </center>  
</div>



### Spring Boot

1. 通过`@Autowired`来自动注入依赖
2. 通过主程序添加注解`@MapperScan`来扫描`Mapper`包

#### 查询

通过`service.list()`查询

#### 删除（逻辑）

配置一个`DeleteMapping`，中间需要一个`{id}`占位符获取路径参数。路径参数`xxx/xxx/var`，逻辑删除操作见[逻辑删除](####删除操作)

1. 配置*swagger*来测试删除请求

   * 具体是重新在一个子模块`common`里面创建子子模块`service_base`中 `edu.gdut.servicebase`创建`SwaggerConfig`

     `SwaggerConfig`的固定写法如下：

     ~~~java
     package edu.gdut.servicebase;
     
     import com.google.common.base.Predicates;
     import org.springframework.context.annotation.Bean;
     import org.springframework.context.annotation.Configuration;
     import springfox.documentation.builders.ApiInfoBuilder;
     import springfox.documentation.builders.PathSelectors;
     import springfox.documentation.service.ApiInfo;
     import springfox.documentation.service.Contact;
     import springfox.documentation.spi.DocumentationType;
     import springfox.documentation.spring.web.plugins.Docket;
     import springfox.documentation.swagger2.annotations.EnableSwagger2;
     
     @Configuration//配置类
     @EnableSwagger2 //swagger注解
     public class SwaggerConfig {
     
         @Bean
         public Docket webApiConfig(){
             return new Docket(DocumentationType.SWAGGER_2)
                     .groupName("webApi")
                     .apiInfo(webApiInfo())
                     .select()
                     .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                     .paths(Predicates.not(PathSelectors.regex("/error.*")))
                     .build();
     
         }
     
         private ApiInfo webApiInfo(){
     
             return new ApiInfoBuilder()
                     .title("网站-课程中心API文档")
                     .description("本文档描述了课程中心微服务接口定义")
                     .version("1.0")
                     .contact(new Contact("java", "http://yongchill.com", "1123@qq.com"))
                     .build();
         }
     }
     ~~~

2. 在需要用到`swagger`的类中引入该依赖，并且在用到`swagger`的类主启动程序中更改扫描包规则`@ComponentScan(basePackages = "edu.gdut")`

### 数据库设计

1. [阿里巴巴开发手册](https://developer.aliyun.com/special/tech-java)

### 开发工具

#### Lombok

1. 通过给`pojo`添加注解`@Data`来实现省去构造方法和*setterandgetter*