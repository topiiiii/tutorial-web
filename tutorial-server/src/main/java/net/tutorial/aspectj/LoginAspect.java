package net.tutorial.aspectj;

import lombok.extern.slf4j.Slf4j;
import net.tutorial.domain.User;
import net.tutorial.exception.ServiceException;
import net.tutorial.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 登录校验切面类
 *
 * @author top
 * @date 2024/6/21 16:23
 */
@Aspect
@Component
@Slf4j
public class LoginAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* net.tutorial.service.*.*(..)) && @annotation(net.tutorial.annotation.IsLogin)")
    public void isLoginPointCut(){}

    /**
     * 前置通知，再通知前校验登录
     * @param joinPoint
     */
    @Before("isLoginPointCut()")
    public void IsLogin(JoinPoint joinPoint){

        User user = SecurityUtils.getLoginUserByApp().getUser();
        if (user==null) {
            throw new ServiceException("请重新登录");
        }

    }
}
