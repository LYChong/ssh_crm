package cn.chzu.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.chzu.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
//	private HibernateTemplate hibernateTemplate;
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
	
	
	//登录的方法
	@SuppressWarnings("all")
	public User loginUser(User user) {
		//调用方法可以得到hibernateTemplate对象
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		//登录查询操作
		//根据用户名密码，查询
		List<User> list = (List<User>) hibernateTemplate.
				find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		//返回user对象
		if(list.size()!=0 && list.size()!=0) {
			User userExUser = list.get(0);
			return userExUser;
		}else {
			return null;
		}
		
		
	}

	//查询所有用户
	public List<User> findAll() {
		return (List<User>) this.getHibernateTemplate().find("from User");
	}
	

	
	
	
}
