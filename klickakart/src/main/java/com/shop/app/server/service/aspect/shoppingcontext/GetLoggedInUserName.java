package com.shop.app.server.service.aspect.shoppingcontext;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.shop.app.server.businessservice.shoppingcontext.retail.FetchLoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class GetLoggedInUserName {

    @Autowired
    private FetchLoggedInUser fetchloggedinuser;

    @After("afterauthenticate()")
    public void afterAuthenticateServiceauthenticate(JoinPoint joinPoint) throws Throwable {
        fetchloggedinuser.fetchLoggedInUser();
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.aaa.AuthenticateService.authenticate(..))")
    public void afterauthenticate() {
    }
}
