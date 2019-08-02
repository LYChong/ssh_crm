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
	
	
	//��¼�ķ���
	@SuppressWarnings("all")
	public User loginUser(User user) {
		//���÷������Եõ�hibernateTemplate����
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		//��¼��ѯ����
		//�����û������룬��ѯ
		List<User> list = (List<User>) hibernateTemplate.
				find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		//����user����
		if(list.size()!=0 && list.size()!=0) {
			User userExUser = list.get(0);
			return userExUser;
		}else {
			return null;
		}
		
		
	}

	//��ѯ�����û�
	public List<User> findAll() {
		return (List<User>) this.getHibernateTemplate().find("from User");
	}
	

	
	
	
}
