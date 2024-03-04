package com.mayikt.service;

import com.mayikt.dao.UserDao;
import com.mayikt.entity.UserEntity;

public class UserService {
	private UserDao userDao = new UserDao();
	public int registUser(UserEntity userEntity){
		// 根据手机号码查询该用户是否已经注册过，如果已经注册过，就无法注册
		String phone = userEntity.getPhone();
		UserEntity dbUserEntity = userDao.getByPhoneUser(phone);
		if(dbUserEntity != null){
			System.out.println("该手机号已经注册");
			return 0; // 返回0或-1
		}
		int result = userDao.registUser(userEntity);
		return result;
	}

	public UserEntity login(UserEntity userEntity){
		return userDao.login(userEntity);
	}
}
