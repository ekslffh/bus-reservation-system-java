package controller;

import java.util.List;

import dao.DriveDAO;
import dto.DriveDTO;

public class MainController {

	public static void main(String[] args) {
		MemberController memberController = new MemberController();
		memberController.run();
	}
	
}
