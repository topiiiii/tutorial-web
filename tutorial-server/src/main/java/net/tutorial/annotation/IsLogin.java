package net.tutorial.annotation;

import java.lang.annotation.*;

/**
 * 检查是否登录注解
 *
 * @author top
 * @date 2024/6/21 16:19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsLogin {

}
