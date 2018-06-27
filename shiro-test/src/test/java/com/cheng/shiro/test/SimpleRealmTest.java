package com.cheng.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author chengchenrui
 * @version Id: SimpleRealmTest.java, v 0.1 2018/6/27 21:46 chengchenrui Exp $$
 */
public class SimpleRealmTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("Mark", "123456", "admin");
    }

    @Test
    public void testAuthentication() {
        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2.主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);
        System.out.println("是否登录:" + subject.isAuthenticated());

        subject.checkRoles("admin"); //授权
        subject.checkPermission("user:delete");

        subject.logout();
        System.out.println("是否登录:" + subject.isAuthenticated());
    }
}