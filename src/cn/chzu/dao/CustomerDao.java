package cn.chzu.dao;

import java.util.List;

import cn.chzu.entity.Customer;
import cn.chzu.entity.Dict;
//�ӿ�
public interface CustomerDao extends BaseDao<Customer>{
	//��ӿͻ�
//	 void add(Customer customer);
	 
	 //�ͻ��б���
//	 List<Customer> findAll();
	 
	//����ID��ѯ����
//	 Customer findOne(int cid);
	 
	 //ɾ���ͻ�
//	 void delete(Customer c);

	 //�޸��û���Ϣ
//	 void update(Customer c);

	 
	 //��ҳ
	 //�ܼ�¼��
	 int findCount();
	 //ÿһҳ�Ŀͻ���Ϣ��list����
	 List<Customer> findPage(int begin, int pageSize);

	 //������ѯ
	 List<Customer> findCondition(Customer customer);
	 
	 //�ۺϲ�ѯ����������ѯ
	 List<Customer> findMoreCondition(Customer customer);
	
	 //��ѯ���пͻ�����
	 List<Dict> findAllDictLevel();
	 
	 //���ݿͻ���Դ����ͳ��
	 List findCountSource();
	 
	 //���ݿͻ��������ͳ��
	 List findCountLevel();

	 
}
