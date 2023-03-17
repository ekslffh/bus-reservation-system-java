package dao;

import java.util.List;

import dto.MemberDTO;

public interface DaoIfs<T> {
	List<T> findAll(); // select * from bus_company
	T findById(String id); // select * from bus_company where c_code=1
	T findByPw(String id);
	int insert(T dto); // insert into bus_company (c_code, c_name, c_address, c_telno) values ('1','홍길동','대전서구월평1234','01084819654')
	int update(T dto); // update bus_company set c_name='전우치' where c_code = ?;
	int deleteById(String id); // delete from bus_company where c_code = 1
}
