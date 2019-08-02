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
	//添加客户操作
	public void add(Customer customer) {
		customerDao.add(customer);
		
	}
	//找到所有客户，列表功能
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
	//根据ID查询客户
	public Customer findOne(int cid) {
		return customerDao.find(cid);
	}
	
	//根据目标对象删除客户
	public void delete(Customer c) {
		customerDao.delete(c);
	}
	
	//修改操作
	public void update(Customer c) {
		customerDao.update(c);
	}
	
	
	//分页数据封装到pagebean对象里面
	public PageBean listPage(Integer currentPage) {
		//创建pageBean对象
		PageBean pageBean = new PageBean();
		//当前页
		pageBean.setCurrentPage(currentPage);
		//总记录数
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		//每页显示记录数
		int pageSize = 3;
		//总页数
		//-- 总记录数除以每页显示记录数，
		//如果能够整除，结果是相除结果
		//如果不能整除，结果是相除的结果+1
		int totalPage = 0;
		if (totalCount%pageSize==0) {   //能够整除
			totalPage = totalCount%pageSize;
		}else {
			totalPage = totalCount%pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		//开始位置计算
		int begin = (currentPage-1)*pageSize;
		//每页记录的list集合
		List<Customer> list = customerDao.findPage(begin,pageSize);
		pageBean.setList(list);
		//返回封装好数据的pageBean对象
		return pageBean;
	}
	
	//条件查询
	public List<Customer> findCondition(Customer customer) {
		return customerDao.findCondition(customer);
	}
	
	//综合查询，多条件查询
	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
	}
	
	//查询所有客户级别
	public List<Dict> findAllDictLevel() {
		return customerDao.findAllDictLevel();
	}
	
	//根据客户来源进行统计
	public List findCountSource() {
		return customerDao.findCountSource();
	}
	
	//根据客户级别进行统计
	public List findCountLevel() {
		return customerDao.findCountLevel();
	}
	
}
