package cn.chzu.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

	private Class pClass;

	//���췽��
	public BaseDaoImpl() {
		//��һ��  �õ���ǰ�������class
		Class clazz = this.getClass();
		
		//�ڶ��� �õ���ǰ��ĸ���Ĳ���������
		Type type = clazz.getGenericSuperclass();
		
		//3 ת�����ӽӿ�ParameterizedType
		ParameterizedType ptype = (ParameterizedType) type;
				
		//4 ��ȡʵ�����Ͳ���
		//���� Map<key,value>
		Type[] types = ptype.getActualTypeArguments();
		
		//5 ��Type���class
		Class tclass =  (Class) types[0];
		
		this.pClass = tclass;

	}

	//���Ӳ���
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	//ɾ������
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//���²���
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//����ID��ѯ
	public T find(int id) {
		return (T) this.getHibernateTemplate().get(pClass, id);
	}


	//��ѯ����
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}
	
}
