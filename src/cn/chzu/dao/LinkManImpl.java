package cn.chzu.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


import cn.chzu.entity.LinkMan;

public class LinkManImpl extends HibernateDaoSupport implements LinkManDao{

	//�����ϵ����Ϣ
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	//��ϵ���б���
	@SuppressWarnings("all")
	public List<LinkMan> listLinkMan() {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}

	//����ID��ѯ��ϵ�˶���
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}

	//�޸���ϵ����Ϣ
	public void updateLinkMan(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	//��ϵ�˶�������ѯ
	public List<LinkMan> findCondition(LinkMan linkMan) {
		
		String hql = "from LinkMan where 1=1 ";
		List<Object> p = new ArrayList<Object>();
		//�ж�����ֵ�Ƿ�Ϊ��
		if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {
			hql += "and lkmName=?";
			p.add(linkMan.getLkmName());
		}
		//�ж��Ƿ�ѡ��ͻ�
		if (linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
			hql += "and customer.cid=?";
			p.add(linkMan.getCustomer().getCid());
		}
		return (List<LinkMan>) this.getHibernateTemplate().find(hql, p.toArray());
	}


}
