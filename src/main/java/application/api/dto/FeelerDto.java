package application.api.dto;

import java.io.Serializable;

import application.model.Feeler;
import application.model.PreviousFeelings;

public class FeelerDto implements BaseDto<Feeler>, Serializable {

	private static final long serialVersionUID = 8266488668414110150L;

	private Long id;
	private String name;
	private String surname;
	private String email;
	private String gender;
	private Integer age;
	private String username;
	private String password;
	private String lastFelt;
	private String timesExpressed;
	private PreviousFeelings previousFeelings;

	public FeelerDto() {
	}

	public FeelerDto(String name, String surname, String email, String gender,
			Integer age, String username, String password, String lastFelt,
			String timesExpressed, PreviousFeelings previousFeelings) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.username = username;
		this.password = password;
		this.lastFelt = lastFelt;
		this.timesExpressed = timesExpressed;
		this.previousFeelings = previousFeelings;
	}

	public FeelerDto(Long id, String name, String surname, String email,
			String gender, Integer age, String username, String password,
			String lastFelt, String timesExpressed,
			PreviousFeelings previousFeelings) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.gender = gender;
		this.age = age;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public PreviousFeelings getPreviousFeelings() {
		return previousFeelings;
	}

	public void setPreviousFeelings(PreviousFeelings previousFeelings) {
		this.previousFeelings = previousFeelings;
	}

	@Override
	public Feeler toEntity() {
		return new Feeler(id, name, surname, email, gender, age, username,
				password, lastFelt, timesExpressed, previousFeelings);
	}

	@Override
	public FeelerDto fromEntity(Feeler entity) {
		return new FeelerDto(id, name, surname, email, gender, age, username,
				password, lastFelt, timesExpressed, previousFeelings);
	}

}
