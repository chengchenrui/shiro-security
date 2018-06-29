package com.cheng.shiro.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cheng.shiro.vo.User;

/**
 * @author chengchenrui
 * @version Id: UserDao.java, v 0.1 2018/6/29 1:05 chengchenrui Exp $$
 */
@Component
public interface UserDao {
    
    User getUserByUsername(String username);

    List<String> getRolesByUserName(String username);
}