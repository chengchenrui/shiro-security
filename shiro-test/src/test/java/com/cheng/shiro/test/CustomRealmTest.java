package com.cheng.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.cheng.shiro.realm.CustomRealm;

/**
 * @author chengchenrui
 * @version Id: CustomRealmTest.java, v 0.1 2018/6/27 21:22 chengchenrui Exp $$
 */
public class CustomRealmTest {

    private CustomRealm customRealm = new CustomRealm();

    @Test
    public void testCustomRealm() {
        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        // 2.声明CustomRealm使用了Md5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        // 3.主题提交认证请求
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