package cn.chzu.dao;

import java.util.List;

import cn.chzu.entity.LinkMan;

public interface LinkManDao {

	//�����ϵ����Ϣ
	void add(LinkMan linkMan);

	List<LinkMan> listLinkMan();

	LinkMan findOne(int linkid);

	void updateLinkMan(LinkMan linkMan);

	//��ϵ��������ѯ
	List<LinkMan> findCondition(LinkMan linkMan);


}
