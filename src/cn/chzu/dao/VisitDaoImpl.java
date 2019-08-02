package cn.chzu.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.chzu.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	//��ӿͻ��ݷù�ϵ
	public void addVisit(Visit visit) {
		this.getHibernateTemplate().save(visit);
	}

	//��ϵ�б�
	public List<Visit> findAll() {
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}
	
}
