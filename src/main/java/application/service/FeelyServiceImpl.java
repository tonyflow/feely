package application.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

import application.api.FeelyService;
import application.api.dto.FeelerDto;
import application.api.dto.FeelingDto;
import application.dao.FeelerRepository;
import application.dao.FeelingRepository;
import application.model.Feeler;
import application.model.Feeling;

@Component
public class FeelyServiceImpl implements FeelyService {

	@Autowired
	FeelerRepository feelerRepository;

	@Autowired
	FeelingRepository feelingRepository;

	@Override
	public FeelerDto addFeeler(FeelerDto feeler) {
		Feeler f = feelerRepository.save(feeler.toEntity());
		return (new FeelerDto()).fromEntity(f);
	}

	@Override
	public boolean deleteFeeler(String username) {

		feelerRepository.deleteByUsername(username);
		if (feelerRepository.findByUsername(username) == null) {
			return true;
		}
		else {
			return false;
		}

	}

	@Override
	public FeelingDto addFeeling(FeelingDto feeling) {
		Feeling f = feelingRepository.save(feeling.toEntity());
		return (new FeelingDto()).fromEntity(f);
	}

	@Override
	public boolean deleteFeeling(String name) {
		feelingRepository.deleteByName(name);
		if (feelingRepository.findByName(name) == null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Iterable<Feeler> listFeelers(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<FeelingDto> listFeeling(Integer id) {
		Iterable<Feeling> l = feelingRepository.findAll(ImmutableList.of(id));
		return StreamSupport.stream(l.spliterator(), true).map((f)->
			new FeelingDto(f.getName(), f.getTimbre(), f.getPotency())
		).collect(Collectors.toList());
	}
}
