package application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.api.IndicoService;
import application.api.UserInfoService;
import application.api.dto.BusinessResponseDto;
import application.api.dto.DiaryEntryDto;
import application.api.dto.DiaryStatisticsDto;

@RestController
@RequestMapping(value = "/diary")
public class DiaryController {

	@Autowired
	private IndicoService indicoService;

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BusinessResponseDto add(@RequestBody DiaryEntryDto diaryEntry) {
		return null;

	}

	@RequestMapping(value = "/evaluate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public DiaryStatisticsDto evaluate(@RequestParam(required = true) Long id) {
		return null;
	}
}
