package cn.chzu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.chzu.dao.CustomerDao;
import cn.chzu.entity.Customer;
import cn.chzu.entity.Dict;
import cn.chzu.entity.PageBean;

@Transactional
public class CustomerService {
	
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	//��ӿͻ�����
	public void add(Customer customer) {
		customerDao.add(customer);
		
	}
	//�ҵ����пͻ����б���
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
	//����ID��ѯ�ͻ�
	public Customer findOne(int cid) {
		return customerDao.find(cid);
	}
	
	//����Ŀ�����ɾ���ͻ�
	public void delete(Customer c) {
		customerDao.delete(c);
	}
	
	//�޸Ĳ���
	public void update(Customer c) {
		customerDao.update(c);
	}
	
	
	//��ҳ���ݷ�װ��pagebean��������
	public PageBean listPage(Integer currentPage) {
		//����pageBean����
		PageBean pageBean = new PageBean();
		//��ǰҳ
		pageBean.setCurrentPage(currentPage);
		//�ܼ�¼��
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		//ÿҳ��ʾ��¼��
		int pageSize = 3;
		//��ҳ��
		//-- �ܼ�¼������ÿҳ��ʾ��¼����
		//����ܹ������������������
		//����������������������Ľ��+1
		int totalPage = 0;
		if (totalCount%pageSize==0) {   //�ܹ�����
			totalPage = totalCount%pageSize;
		}else {
			totalPage = totalCount%pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		//��ʼλ�ü���
		int begin = (currentPage-1)*pageSize;
		//ÿҳ��¼��list����
		List<Customer> list = customerDao.findPage(begin,pageSize);
		pageBean.setList(list);
		//���ط�װ�����ݵ�pageBean����
		return pageBean;
	}
	
	//������ѯ
	public List<Customer> findCondition(Customer customer) {
		return customerDao.findCondition(customer);
	}
	
	//�ۺϲ�ѯ����������ѯ
	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
	}
	
	//��ѯ���пͻ�����
	public List<Dict> findAllDictLevel() {
		return customerDao.findAllDictLevel();
	}
	
	//���ݿͻ���Դ����ͳ��
	public List findCountSource() {
		return customerDao.findCountSource();
	}
	
	//���ݿͻ��������ͳ��
	public List findCountLevel() {
		return customerDao.findCountLevel();
	}
	
}
