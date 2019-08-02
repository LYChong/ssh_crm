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

	
	 //��� �ͻ��Ĺ���
//	public void add(Customer customer) {
//		this.getHibernateTemplate().save(customer);}
	 

//	@SuppressWarnings("all")
//	public List<Customer> findAll() {
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");	}

	//����ID���в�ѯ
//	public Customer findOne(int cid) {
//		return this.getHibernateTemplate().get(Customer.class, cid);	}

	//ɾ���ͻ�
//	public void delete(Customer c) {
//		this.getHibernateTemplate().delete(c);	}

	//�޸Ŀͻ���Ϣ
//	public void update(Customer c) {
//		this.getHibernateTemplate().update(c);	}

	
	//��ҳ
	//��ѯ��¼��
	@SuppressWarnings("all")
	public int findCount() {
		//����hibernateTemplate�����find����ʵ��
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		//��list�н�ֵ�õ�
		if (list!=null&&list.size()!=0) {
			Object obj = list.get(0);
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
		
	}

	//��ѯÿҳ�Ŀͻ���Ϣ
	@SuppressWarnings("all")
	public List<Customer> findPage(int begin, int pageSize) {
		//ʹ�����߶����hibernateTamplate�ķ���ʵ��  (��Ҳ����)
		//1���������߶������ö��ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//����hibernateģ������ķ���
		List<Customer> list =  (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}
	
	
	//������ѯ
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().
				find("from Customer where custName like?", "%"+customer.getCustName()+"%");
		return list;
	}

	//�ۺϲ�ѯ����������ϲ�ѯ
	@SuppressWarnings("all")
	public List<Customer> findMoreCondition(Customer customer) {
		//ʹ��hibernateģ��ʵ��
		//����ƴ�ӵ�hql���
		String hql = "from Customer where 1=1 ";
		//����list���ϣ����ֵ��Ϊ�հ�ֵ���õ�list������ȥ
		List<Object> p = new ArrayList<Object>();
		//���û��ѡ��ͻ������򽫿ͻ��������ó�null
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

	//��ѯ���еĿͻ�����
	@SuppressWarnings("all")
	public List<Dict> findAllDictLevel() {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	//���ݿͻ���Դ����ͳ��
	public List findCountSource() {
		//���õײ�sqlʵ��
		//SQLquery����
		//1���õ�sessionFactory����,�õ��뱾���̰߳󶨵�session
		Session session = (Session) this.getSessionFactory().getCurrentSession();
		//2������SQLQuery����
		SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(*) AS num,custSource FROM t_customer GROUP BY custSource");
		//3�������ؽ��
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		//���÷����õ����
		List list = sqlQuery.list();
		return list;
	}

	//���ݿͻ��������ͳ��
	public List findCountLevel() {
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("SELECT c.num,d.dname FROM (SELECT COUNT(*) AS num,custLevel FROM t_customer GROUP BY custLevel) c, t_dict d WHERE c.custLevel=d.did");
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}

}
