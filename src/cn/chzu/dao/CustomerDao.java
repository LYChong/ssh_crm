package cn.chzu.dao;

import java.util.List;

import cn.chzu.entity.Customer;
import cn.chzu.entity.Dict;
//接口
public interface CustomerDao extends BaseDao<Customer>{
	//添加客户
//	 void add(Customer customer);
	 
	 //客户列表功能
//	 List<Customer> findAll();
	 
	//根据ID查询对象
//	 Customer findOne(int cid);
	 
	 //删除客户
//	 void delete(Customer c);

	 //修改用户信息
//	 void update(Customer c);

	 
	 //分页
	 //总记录数
	 int findCount();
	 //每一页的客户信息的list集合
	 List<Customer> findPage(int begin, int pageSize);

	 //条件查询
	 List<Customer> findCondition(Customer customer);
	 
	 //综合查询，多条件查询
	 List<Customer> findMoreCondition(Customer customer);
	
	 //查询所有客户级别
	 List<Dict> findAllDictLevel();
	 
	 //根据客户来源进行统计
	 List findCountSource();
	 
	 //根据客户级别进行统计
	 List findCountLevel();

	 
}
