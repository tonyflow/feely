package application.api.dto;

import java.io.Serializable;

import application.model.Feeling;

public class FeelingDto implements BaseDto<Feeling>, Serializable {

	private static final long serialVersionUID = -2863345716557922556L;
	private Integer id;
	private String name;
	private String timbre;
	private Integer potency;

	public FeelingDto() {
	}

	public FeelingDto(String name, String timbre, Integer potency) {
		super();
		this.name = name;
		this.timbre = timbre;
		this.potency = potency;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimbre() {
		return timbre;
	}

	public void setTimbre(String timbre) {
		this.timbre = timbre;
	}

	public Integer getPotency() {
		return potency;
	}

	public void setPotency(Integer potency) {
		this.potency = potency;
	}

	@Override
	public Feeling toEntity() {
		// TODO Auto-generated method stub
		return new Feeling(name, timbre, potency);
	}

	@Override
	public FeelingDto fromEntity(Feeling entity) {
		// TODO Auto-generated method stub
		return new FeelingDto(entity.getName(), entity.getTimbre(), entity.getPotency());
	}

}
