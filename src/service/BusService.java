package service;

import java.util.List;

import dao.BusDAO;
import dto.BusDTO;

public class BusService {
	private BusDAO dao = new BusDAO();
	public void findAll() {
		List<BusDTO> list = dao.findAll();
		
	}

}
