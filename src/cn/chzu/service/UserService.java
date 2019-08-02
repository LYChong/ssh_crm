package cn.chzu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.chzu.dao.UserDao;
import cn.chzu.entity.User;

@Transactional
public class UserService {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public  User login(User user) {
		return userDao.loginUser(user);
	}
	
	public List<User> findAll() {
		return userDao.findAll();
	}
	

}
