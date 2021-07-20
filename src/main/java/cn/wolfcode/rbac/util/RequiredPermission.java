package cn.wolfcode.rbac.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//指定注解贴的位置
@Retention(RetentionPolicy.RUNTIME)//定义该注解保留的时间，描述该注解的生命周期
public @interface RequiredPermission {
    //表示注解括号中写的都是value值
    String[] value();
}
