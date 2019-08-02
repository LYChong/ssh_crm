package cn.chzu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;


import cn.chzu.entity.Customer;
import cn.chzu.entity.Dict;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

	
	 //添加 客户的功能
//	public void add(Customer customer) {
//		this.getHibernateTemplate().save(customer);}
	 

//	@SuppressWarnings("all")
//	public List<Customer> findAll() {
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");	}

	//根据ID进行查询
//	public Customer findOne(int cid) {
//		return this.getHibernateTemplate().get(Customer.class, cid);	}

	//删除客户
//	public void delete(Customer c) {
//		this.getHibernateTemplate().delete(c);	}

	//修改客户信息
//	public void update(Customer c) {
//		this.getHibernateTemplate().update(c);	}

	
	//分页
	//查询记录数
	@SuppressWarnings("all")
	public int findCount() {
		//调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		//从list中将值得到
		if (list!=null&&list.size()!=0) {
			Object obj = list.get(0);
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
		
	}

	//查询每页的客户信息
	@SuppressWarnings("all")
	public List<Customer> findPage(int begin, int pageSize) {
		//使用离线对象和hibernateTamplate的方法实现  (我也不会)
		//1、创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//调用hibernate模板里面的方法
		List<Customer> list =  (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}
	
	
	//条件查询
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().
				find("from Customer where custName like?", "%"+customer.getCustName()+"%");
		return list;
	}

	//综合查询，多条件组合查询
	@SuppressWarnings("all")
	public List<Customer> findMoreCondition(Customer customer) {
		//使用hibernate模板实现
		//创建拼接的hql语句
		String hql = "from Customer where 1=1 ";
		//创建list集合，如果值不为空把值设置到list集合中去
		List<Object> p = new ArrayList<Object>();
		//如果没有选择客户级别，则将客户级别设置成null
		if(customer.getDictCustLevel().getDid().equals("0")) {
			customer.getDictCustLevel().setDid(null);
		}
		if (customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			hql += "and custName=?"; 
			p.add(customer.getCustName());
		}if(customer.getDictCustLevel().getDid()!=null && !"".equals(customer.getDictCustLevel().getDid())) {
			hql += "and custLevel=?";
			p.add(customer.getDictCustLevel().getDid());
		}if(customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
			hql += "and custSource=?";
			p.add(customer.getCustSource());
		}
		System.out.println("hql: "+hql);
		System.out.println("list: "+p);
			
		return (List<Customer>) this.getHibernateTemplate().find(hql,p.toArray());
	}

	//查询所有的客户级别
	@SuppressWarnings("all")
	public List<Dict> findAllDictLevel() {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	//根据客户来源进行统计
	public List findCountSource() {
		//调用底层sql实现
		//SQLquery对象
		//1、得到sessionFactory对象,得到与本地线程绑定的session
		Session session = (Session) this.getSessionFactory().getCurrentSession();
		//2、创建SQLQuery对象
		SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(*) AS num,custSource FROM t_customer GROUP BY custSource");
		//3、处理返回结果
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		//调用方法得到结果
		List list = sqlQuery.list();
		return list;
	}

	//根据客户级别进行统计
	public List findCountLevel() {
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("SELECT c.num,d.dname FROM (SELECT COUNT(*) AS num,custLevel FROM t_customer GROUP BY custLevel) c, t_dict d WHERE c.custLevel=d.did");
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}

}
