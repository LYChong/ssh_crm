package cn.chzu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.chzu.dao.LinkManDao;
import cn.chzu.entity.LinkMan;

@Transactional
public class LinkManService {
	//����ע��
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	//�����ϵ����Ϣ
	public void add(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	//��ϵ���б���
	public List<LinkMan> listLinkMan() {
		return linkManDao.listLinkMan();
	}

	//����ID��ѯ��ϵ�˶���
	public LinkMan findOne(int linkid) {
		return linkManDao.findOne(linkid);
	}

	//�޸���ϵ����Ϣ
	public void updateLinkMan(LinkMan linkMan) {
		linkManDao.updateLinkMan(linkMan);
	}

	//��ϵ�˶�������ѯ
	public List<LinkMan> findCondition(LinkMan linkMan) {
		return linkManDao.findCondition(linkMan);
	}
	
}
