package application.api;

import application.api.dto.FeelerDto;
import application.api.dto.FeelingDto;
import application.model.Feeler;
import application.model.Feeling;

public interface FeelyService {

	public Iterable<Feeler> listFeelers(Integer id);

	FeelerDto addFeeler(FeelerDto feeler);

	boolean deleteFeeler(String username);

	public Iterable<FeelingDto> listFeeling(Integer id);

	FeelingDto addFeeling(FeelingDto feeling);

	boolean deleteFeeling(String name);

	public boolean login(String username, String password);

	public boolean register(FeelerDto feeler);
}
