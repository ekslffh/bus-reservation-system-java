package service;

import java.util.List;

import dao.BusCompanyDAO;
import dto.BusCompanyDTO;

public class BusCompanyService {
	private BusCompanyDAO dao = new BusCompanyDAO();
	public void findAll() {
		List<BusCompanyDTO> list = dao.findAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
