package controller;

import service.BusCompanyService;

public class BusCompanyController {
	private BusCompanyService service = new BusCompanyService();
	
	
	public void findAll() {
		service.findAll();
	}
}
