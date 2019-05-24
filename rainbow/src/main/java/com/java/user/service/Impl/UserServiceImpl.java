package com.java.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.user.dao.UserDao;
import com.java.user.entity.User;
import com.java.user.service.UserService;
import com.sun.istack.internal.logging.Logger;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	public UserDao userDao;
	
	Logger logger = Logger.getLogger(UserServiceImpl.class);
	

	public User selectUser(User user) {
		return userDao.selectUser(user);
	}

}
