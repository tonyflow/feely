package application.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "FEELING", schema = "FEELY")
public class Feeling implements Serializable {

	private static final long serialVersionUID = 8335211531203732970L;

	private Integer id;
	private String name;
	private String timbre;
	private Integer potency;

	public Feeling() {
	}

	public Feeling(String name, String timbre, Integer potency) {
		super();
		this.name = name;
		this.timbre = timbre;
		this.potency = potency;
	}

	public Feeling(Integer id, String name, String timbre, Integer potency) {
		super();
		this.id = id;
		this.name = name;
		this.timbre = timbre;
		this.potency = potency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TIMBRE", nullable = false)
	public String getTimbre() {
		return timbre;
	}

	public void setTimbre(String timbre) {
		this.timbre = timbre;
	}

	@Column(name = "POTENCY")
	public Integer getPotency() {
		return potency;
	}

	public void setPotency(Integer potency) {
		this.potency = potency;
	}

}
