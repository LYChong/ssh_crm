package cn.chzu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.chzu.dao.VisitDao;
import cn.chzu.entity.Visit;

@Transactional
public class VisitService {
	private VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	//��ӿͻ��ݷù�ϵ
	public void addVisit(Visit visit) {
		this.visitDao.addVisit(visit);
	}

	//��ϵ�б�
	public List<Visit> findAll() {
		return this.visitDao.findAll();
	}
	

}
