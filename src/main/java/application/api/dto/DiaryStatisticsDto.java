package application.api.dto;

import java.io.Serializable;

public class DiaryStatisticsDto implements Serializable{

	private static final long serialVersionUID = -971915650483316142L;

	private Double positivityMeasure;

	public DiaryStatisticsDto(Double positivityMeasure) {
		this.positivityMeasure = positivityMeasure;
	}

	public Double getPositivityMeasure() {
		return positivityMeasure;
	}

	public void setPositivityMeasure(Double positivityMeasure) {
		this.positivityMeasure = positivityMeasure;
	}
	
	
}
