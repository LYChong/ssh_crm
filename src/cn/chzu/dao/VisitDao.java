package cn.chzu.dao;

import java.util.List;

import cn.chzu.entity.Visit;

public interface VisitDao {
	
	//��ӿͻ��ݷù�ϵ
	void addVisit(Visit visit);

	//��ϵ�б�
	List<Visit> findAll();

}
