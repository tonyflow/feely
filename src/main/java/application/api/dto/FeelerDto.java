package application.api.dto;

import java.io.Serializable;

import application.model.Feeler;
import application.model.PreviousFeelings;

public class FeelerDto implements BaseDto<Feeler>, Serializable {

	private static final long serialVersionUID = 8266488668414110150L;

	private Long id;
	private String username;
	private String password;
	private String lastFelt;
	private String timesExpressed;
	private PreviousFeelings previousFeelings;

	public FeelerDto() {
	}

	public FeelerDto(Long id, String username, String password, String lastFelt, String timesExpressed, PreviousFeelings previousFeelings) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastFelt = lastFelt;
		this.timesExpressed = timesExpressed;
		this.previousFeelings = previousFeelings;
	}

	public FeelerDto(String username, String password, String lastFelt, String timesExpressed, PreviousFeelings previousFeelings) {
		super();
		this.username = username;
		this.password = password;
		this.lastFelt = lastFelt;
		this.timesExpressed = timesExpressed;
		this.previousFeelings = previousFeelings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastFelt() {
		return lastFelt;
	}

	public void setLastFelt(String lastFelt) {
		this.lastFelt = lastFelt;
	}

	public String getTimesExpressed() {
		return timesExpressed;
	}

	public void setTimesExpressed(String timesExpressed) {
		this.timesExpressed = timesExpressed;
	}

	@Override
	public Feeler toEntity() {
		return new Feeler(this.id, this.username, this.password, this.lastFelt, this.timesExpressed, this.previousFeelings);
	}

	@Override
	public FeelerDto fromEntity(Feeler entity) {
		return new FeelerDto(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getLastFelt(), entity.getTimesExpressed(),
				entity.getPreviousFeelings());
	}

}
