package com.putaoteng.task7.dao;


import org.springframework.stereotype.Repository;

import com.putaoteng.task7.model.User;

@Repository (value="userDao")
public interface UserDao extends BasicDao{
	User findByUserName(String username);
}
