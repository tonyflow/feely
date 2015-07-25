package application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.api.FeelyService;

@RestController
@RequestMapping("/feely")
public class FeelyController {

	@Autowired
	FeelyService service;

	@RequestMapping(value = "/add/feeling")
	private void addFeeling() {
		service.addFeeling();
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "/delete/feeling")
	private void deleteFeeling() {
		service.deleteFeeling();
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "/add/feeler")
	private void addFeeler() {
		service.addFeeler();
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "/delete/feeler")
	private void deleteFeeler() {
		service.deleteFeeler();
		// TODO Auto-generated method stub

	}
}
