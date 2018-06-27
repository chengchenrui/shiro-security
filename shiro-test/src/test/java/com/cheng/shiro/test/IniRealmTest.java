package com.cheng.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author chengchenrui
 * @version Id: IniRealmTest.java, v 0.1 2018/6/27 21:43 chengchenrui Exp $$
 */
public class IniRealmTest {

    IniRealm iniRealm = new IniRealm("classpath:user.ini");

    @Test
    public void testIniRealm() {
        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

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