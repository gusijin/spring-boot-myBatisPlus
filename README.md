
# springboot整合mybatisPlus超级详细实例

MyBatis-Plus，如其名所示，是MyBatis的一个功能增强工具。它旨在对MyBatis的核心功能进行拓展，而不会对其原有结构或行为做出任何修改。

## 1环境
系统：windows 11

软件：IntelliJ IDEA 2022.2.3

maven：maven3.8.6

JDK：1.8.0_351

springboot：2.5.3

myBatisPlus：3.3.1

## 2.1新建springboot项目

idea中新建springboot项目


## 2.2 添加Mybatis-plus和mysql依赖

在pom.xml中引入依赖

```xml
<!--Mybatis-plus的依赖-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.1</version>
</dependency>

<!--mysql的依赖-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.31</version>
</dependency>
```

## 2.3 修改配置文件
application.properties改为application.yml

```yml
# 端口号8080
server:
  port: 8080

# 数据库名：mysql，用户名root，密码123456
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://192.168.17.128:3306/test?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
    driver-class-name: com.mysql.cj.jdbc.Driver

# mybatis-plus配置
mybatis-plus:
  # xml文件位置
  mapper-locations: classpath:mapper/*.xml
```

## 2.4 新建包和文件

/src/main/java/com.gsj.springbootmp下新建包
service，controller，mapper，entity

/src/main/resouces下设置mapping文件夹


## 2.5 新建表
新建数据库测试表：
```sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `age` int(11) NOT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='用户表';
```

## 2.6 创建实体类
com/gsj/springbootmp/entity/User.java

```java
package com.gsj.springbootmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

}

```


## 2.7 创建Mapper接口
com/gsj/springbootmp/mapper/UserMapper.java
```java
package com.gsj.springbootmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gsj.springbootmp.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
```
## 2.8 创建Service接口

com/gsj/springbootmp/service/UserService.java
```java
package com.gsj.springbootmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gsj.springbootmp.entity.User;

public interface UserService extends IService<User> {
}

```
## 2.9 创建Service实现类
com/gsj/springbootmp/service/impl/UserServiceImpl.java

```java
package com.gsj.springbootmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gsj.springbootmp.entity.User;
import com.gsj.springbootmp.mapper.UserMapper;
import com.gsj.springbootmp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

```

## 2.10 增删改查
com/gsj/springbootmp/controller/UserController.java


```java
package com.gsj.springbootmp.controller;

import com.gsj.springbootmp.entity.User;
import com.gsj.springbootmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public List<User> list(){
        return userService.list();
    }

    @RequestMapping("getUser/{id}")
    public User getUser(@PathVariable String id){
        return userService.getById(id);
    }

    /**
     * 插入学生信息
     * @param User
     */
    @RequestMapping("/insert")
    public void insertInfo(User User){

        User info=new User();
        info.setName(User.getName());
        info.setAge(User.getAge());
        userService.save(info);
    }

    /**
     * 根据id更新学生表信息
     * @param User
     */
    @RequestMapping("/update")
    public void updateById(User User){

        User info=new User();
        info.setId(User.getId());
        info.setName(User.getName());
        info.setAge(User.getAge());
        userService.updateById(info);
    }

    /**
     * 根据id删除学生信息
     * @param id
     */
    @RequestMapping("/delete")
    public void deleteById(String id){
        userService.removeById(id);
    }
}

```

我们可通过启动应用程序进行测试，以验证其能否顺利运作并准确从数据库提取数据。若一切正常，则表明已成功运用Spring Boot与MyBatis-Plus进行了有效集成。