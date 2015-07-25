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

	private static final long serialVersionUID = 4840834635501393237L;
	private Long id;
	private String username;
	private String password;
	private String lastFelt;
	private String timesExpressed;
	/**
	 * Should hold up to a certain number of feelings
	 */
	private PreviousFeelings previousFeelings;

	public Feeler() {
	}

	public Feeler(String username, String password, String lastFelt, String timesExpressed, PreviousFeelings previousFeelings) {
		super();
		this.username = username;
		this.password = password;
		this.lastFelt = lastFelt;
		this.timesExpressed = timesExpressed;
		this.previousFeelings = previousFeelings;
	}

	public Feeler(Long id, String username, String password, String lastFelt, String timesExpressed, PreviousFeelings previousFeelings) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "Feeler [id=" + id + ", username=" + username + ", password=" + password + ", lastFelt=" + lastFelt + ", timesExpressed=" + timesExpressed + "]";
	}

}
