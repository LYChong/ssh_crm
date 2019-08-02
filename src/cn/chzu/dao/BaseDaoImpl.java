package cn.chzu.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

	private Class pClass;

	//构造方法
	public BaseDaoImpl() {
		//第一步  得到当前运行类的class
		Class clazz = this.getClass();
		
		//第二步 得到当前类的父类的参数化类型
		Type type = clazz.getGenericSuperclass();
		
		//3 转换成子接口ParameterizedType
		ParameterizedType ptype = (ParameterizedType) type;
				
		//4 获取实际类型参数
		//比如 Map<key,value>
		Type[] types = ptype.getActualTypeArguments();
		
		//5 把Type变成class
		Class tclass =  (Class) types[0];
		
		this.pClass = tclass;

	}

	//增加操作
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	//删除操作
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//更新操作
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//根据ID查询
	public T find(int id) {
		return (T) this.getHibernateTemplate().get(pClass, id);
	}


	//查询所有
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}
	
}
