package application.api;

import application.api.dto.DiaryEntryDto;
import application.api.dto.DiaryStatisticsDto;

public interface IndicoService {

	DiaryStatisticsDto evaluate(DiaryEntryDto entry);
	
}
