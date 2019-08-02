package cn.chzu.dao;

import java.util.List;

/**
 * @author ����ɽ
 * T������������
 * @param <T>
 */
public interface BaseDao<T> {
	
	//��
	void add(T t);
	
	//ɾ
	void delete(T t);
	
	//��
	void update(T t);
	
	//�� 1.����ID��ѯ
	T find(int id);
	
	//2.��ѯ����
	List<T> findAll();
}
