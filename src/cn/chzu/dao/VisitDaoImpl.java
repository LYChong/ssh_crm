package cn.chzu.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.chzu.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	//添加客户拜访关系
	public void addVisit(Visit visit) {
		this.getHibernateTemplate().save(visit);
	}

	//关系列表
	public List<Visit> findAll() {
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}
	
}
