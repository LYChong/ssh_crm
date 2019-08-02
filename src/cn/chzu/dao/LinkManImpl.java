package cn.chzu.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


import cn.chzu.entity.LinkMan;

public class LinkManImpl extends HibernateDaoSupport implements LinkManDao{

	//添加联系人信息
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	//联系人列表功能
	@SuppressWarnings("all")
	public List<LinkMan> listLinkMan() {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}

	//根据ID查询联系人对象
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}

	//修改联系人信息
	public void updateLinkMan(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	//联系人多条件查询
	public List<LinkMan> findCondition(LinkMan linkMan) {
		
		String hql = "from LinkMan where 1=1 ";
		List<Object> p = new ArrayList<Object>();
		//判断条件值是否为空
		if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {
			hql += "and lkmName=?";
			p.add(linkMan.getLkmName());
		}
		//判断是否选择客户
		if (linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
			hql += "and customer.cid=?";
			p.add(linkMan.getCustomer().getCid());
		}
		return (List<LinkMan>) this.getHibernateTemplate().find(hql, p.toArray());
	}


}
