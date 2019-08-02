package cn.chzu.dao;

import java.util.List;

import cn.chzu.entity.LinkMan;

public interface LinkManDao {

	//添加联系人信息
	void add(LinkMan linkMan);

	List<LinkMan> listLinkMan();

	LinkMan findOne(int linkid);

	void updateLinkMan(LinkMan linkMan);

	//联系人条件查询
	List<LinkMan> findCondition(LinkMan linkMan);


}
