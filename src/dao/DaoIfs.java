package dao;

import java.util.List;

public interface DaoIfs<T> {
	List<T> findAll(); // select * from bus_company
	T findById(String id); // select * from bus_company where c_code=1
	int insert(T dto); // insert into bus_company (c_code, c_name, c_address, c_telno) values ('1','������', '���� 1234','01084819654')
	int update(T dto); // update bus_company set c_name='ȫ�浿' where c_code = ?;
	int deleteById(String id); // delete from bus_company where c_code = 1
}
