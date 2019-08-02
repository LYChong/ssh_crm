package cn.chzu.dao;

import java.util.List;

import cn.chzu.entity.Visit;

public interface VisitDao {
	
	//添加客户拜访关系
	void addVisit(Visit visit);

	//关系列表
	List<Visit> findAll();

}
