package application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import application.support.PreviousFeelingsConverter;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "FEELER", schema = "FEELY")
public class Feeler implements Serializable {
	
//	ID SERIAL NOT NULL,
//	NAME VARCHAR;
//	SURNAME VARCHAR;
//	EMAIL VARCHAR;
//	GENDER VARCHAR;
//	AGE INTEGER;
//	USERNAME VARCHAR(255),
//	PASSWORD VARCHAR(255),
//	LAST_FELT  VARCHAR(255) NULL,
//	TIMES_EXPRESSED VARCHAR(255),
//	CONSTRAINT FEELER_PK PRIMARY KEY ( ID )

	private static final long serialVersionUID = 4840834635501393237L;
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

	public Feeler() {
	}

	

	public Feeler(Long id, String name, String surname, String email,
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



	public Feeler(String name, String surname, String email, String gender,
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



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USERNAME", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "LAST_FELT")
	public String getLastFelt() {
		return lastFelt;
	}

	public void setLastFelt(String lastFelt) {
		this.lastFelt = lastFelt;
	}

	@Column(name = "TIMES_EXPRESSED", nullable = false)
	public String getTimesExpressed() {
		return timesExpressed;
	}

	public void setTimesExpressed(String timesExpressed) {
		this.timesExpressed = timesExpressed;
	}

	@Column(name = "PREVIOUS_FEELINGS", nullable = true)
	@Convert(converter = PreviousFeelingsConverter.class)
	public PreviousFeelings getPreviousFeelings() {
		return previousFeelings;
	}

	public void setPreviousFeelings(PreviousFeelings previousFeelings) {
		this.previousFeelings = previousFeelings;
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



	@Override
	public String toString() {
		return "Feeler [id=" + id + ", username=" + username + ", password=" + password + ", lastFelt=" + lastFelt + ", timesExpressed=" + timesExpressed + "]";
	}

}
