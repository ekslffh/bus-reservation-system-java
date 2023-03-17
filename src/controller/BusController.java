package controller;

import service.BusService;

public class BusController {
	private BusService service = new BusService();
	
	public void findAll() {
		service.findAll();
	}

}
