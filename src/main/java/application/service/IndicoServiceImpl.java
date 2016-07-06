package application.service;

import java.io.IOException;

import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.api.IndicoService;
import application.api.UserInfoService;
import application.api.dto.DiaryEntryDto;
import application.api.dto.DiaryStatisticsDto;

@Component
public class IndicoServiceImpl implements IndicoService{

	@Autowired
	private UserInfoService service;
	
	@Autowired
	private Indico indico;
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public DiaryStatisticsDto evaluate(DiaryEntryDto entry) {
		IndicoResult res;
		try {
			res = indico.sentiment.predict(entry.getBody());
			return new DiaryStatisticsDto(res.getSentiment());
		} catch (UnsupportedOperationException | IOException | IndicoException e) {
			LOGGER.error("Could not succesfully evaluate entry",e);
		}
		return null;
		
	}

}
