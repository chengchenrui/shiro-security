package com.cheng.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author chengchenrui
 * @version Id: JdbcRealmTest.java, v 0.1 2018/6/27 21:49 chengchenrui Exp $$
 */
public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://118.89.177.110:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    @Test
    public void testAuthentication() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //开启角色权限
        jdbcRealm.setPermissionsLookupEnabled(true);

        //自定义SQL
        //认证查询
        jdbcRealm.setAuthenticationQuery("select password from test_users where username = ?");
        //用户角色查询
        jdbcRealm.setUserRolesQuery("select role_name from test_user_roles where username = ?");

        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        // 2.主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Jerry", "123456");//Mark
        subject.login(token);
        System.out.println("是否登录:" + subject.isAuthenticated());

        //授权
        subject.checkRoles("admin", "user");
        //权限
        subject.checkPermission("user:select");

        subject.logout();
        System.out.println("是否登录:" + subject.isAuthenticated());
    }
}