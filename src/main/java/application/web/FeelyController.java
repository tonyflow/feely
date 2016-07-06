package application.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.api.FeelyService;
import application.api.dto.FeelerDto;
import application.api.dto.FeelingDto;
import application.exceptions.FeelerNotFoundException;
import application.exceptions.FeelingNotFoundException;
import application.exceptions.InvalidCredentialsException;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/feely")
public class FeelyController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	FeelyService service;

	@RequestMapping(value = "/list/feeling", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<FeelingDto> listFeeling(
			@RequestParam(value = "id", required = false) Integer id) {

		logger.info("Received Request");
		if (id == null) {

		}
		Iterable<FeelingDto> list = service.listFeeling(id);
		System.out.println("Listing feelings");
		list.forEach(c -> System.out.println(" - " + c.getName() + " ,"
				+ c.getTimbre()));
		return list;

	}

	@RequestMapping(value = "/add/feeling", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private FeelingDto addFeeling(@RequestBody FeelingDto feeling) {
		return service.addFeeling(feeling);

	}

	@RequestMapping(value = "/delete/feeling", method = RequestMethod.POST)
	private void deleteFeeling(@RequestParam(value = "name") String name) {
		service.deleteFeeling(name);
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "/add/feeler")
	private void addFeeler(@RequestBody FeelerDto feeler) {
		service.addFeeler(feeler);
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "/delete/feeler")
	private String deleteFeeler(
			@RequestParam(value = "username") String username) {
		boolean result = service.deleteFeeler(username);
		if (result) {
			return "Operation successfully completed";
		} else {
			throw new FeelerNotFoundException();
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private void login(
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new InvalidCredentialsException("Empty usernmae of password");
		}

		service.login(username, password);
	}

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private Boolean register(@RequestBody(required = true) FeelerDto feeler) {
		return service.register(feeler);
	}

	@ExceptionHandler(value = { FeelerNotFoundException.class,
			FeelingNotFoundException.class })
	private void handleException() {

	}
}
