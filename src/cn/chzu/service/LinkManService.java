package cn.chzu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.chzu.dao.LinkManDao;
import cn.chzu.entity.LinkMan;

@Transactional
public class LinkManService {
	//属性注入
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	//添加联系人信息
	public void add(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	//联系人列表功能
	public List<LinkMan> listLinkMan() {
		return linkManDao.listLinkMan();
	}

	//根据ID查询联系人对象
	public LinkMan findOne(int linkid) {
		return linkManDao.findOne(linkid);
	}

	//修改联系人信息
	public void updateLinkMan(LinkMan linkMan) {
		linkManDao.updateLinkMan(linkMan);
	}

	//联系人多条件查询
	public List<LinkMan> findCondition(LinkMan linkMan) {
		return linkManDao.findCondition(linkMan);
	}
	
}
