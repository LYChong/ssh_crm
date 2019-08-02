package cn.chzu.dao;

import java.util.List;

/**
 * @author 刘看山
 * T代表任意类型
 * @param <T>
 */
public interface BaseDao<T> {
	
	//增
	void add(T t);
	
	//删
	void delete(T t);
	
	//改
	void update(T t);
	
	//查 1.根据ID查询
	T find(int id);
	
	//2.查询所有
	List<T> findAll();
}
