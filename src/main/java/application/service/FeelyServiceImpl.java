package application.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

import application.api.FeelyService;
import application.api.dto.FeelerDto;
import application.api.dto.FeelingDto;
import application.dao.FeelerRepository;
import application.dao.FeelingRepository;
import application.exceptions.UserAlreadyRegisteredExcpetion;
import application.model.Feeler;
import application.model.Feeling;

@Component
public class FeelyServiceImpl implements FeelyService {

	private static final String FEELY_REGISTRATION_CONFIRMATION = "Feely Registration Confirmation";

	@Autowired
	FeelerRepository feelerRepository;

	@Autowired
	FeelingRepository feelingRepository;

	@Autowired
	private MailSender mailSender;

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
		} else {
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
		} else {
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
		if (id == null) {
			return StreamSupport
					.stream(feelingRepository.findAll().spliterator(), true)
					.map((m) -> {
						return new FeelingDto(m.getName(), m.getTimbre(), m
								.getPotency());
					}).collect(Collectors.toList());
		} else {
			Feeling f = feelingRepository.findOne(id);
			// Arrays.asList(new FeelingDto(f.getName(), f.getTimbre(),
			// f.getPotency()));
			return ImmutableList.of(new FeelingDto(f.getName(), f.getTimbre(),
					f.getPotency()));

		}

	}

	@Override
	public boolean login(String username, String password) {
		return feelerRepository.findByUsernameAndPassword(username, password) != null;
	}

	@Override
	public boolean register(FeelerDto feeler) {
		if (feelerRepository.findByEmail(feeler.getEmail()) != null) {
			throw new UserAlreadyRegisteredExcpetion("User "
					+ feeler.getUsername()
					+ " is already registered with username "
					+ feeler.getUsername());
		}

		feelerRepository.save(feeler.toEntity());

		// send mail for registration confirmation
		// try {
		// MimeMessage mimeMessage =
		// ((JavaMailSenderImpl)mailSender).createMimeMessage();
		// MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		// helper.setTo(feeler.getEmail());
		// helper.setText("this is a verification");
		// mailSender.send(mimeMessage);
		//
		// } catch (Exception e) {
		// throw new RuntimeException("Could not send verification mail");
		// }
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(feeler.getEmail());
		mail.setFrom("info@feely.com");
		mail.setSubject(FEELY_REGISTRATION_CONFIRMATION);
		mail.setText("This is to verify that user "
				+ feeler.getName()
				+ " "
				+ feeler.getSurname()
				+ " has been registered to feely with the following credentials "
				+ feeler.getPassword());

		mailSender.send(mail);
		return true;
	}

}
