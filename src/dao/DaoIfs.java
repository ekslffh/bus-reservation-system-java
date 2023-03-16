package dao;

import java.util.List;

public interface DaoIfs<T> {
	List<T> findAll();
	T findById(String id);
	int save(T dto);
	int update(T dto);
	int delete(String id);
}
