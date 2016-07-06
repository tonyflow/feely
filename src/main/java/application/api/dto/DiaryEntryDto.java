package application.api.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class DiaryEntryDto implements Serializable {

	private static final long serialVersionUID = -3222560656570022447L;
	private String username;
	private ZonedDateTime createdAt;
	private String body;
	private Integer positivity;

	public DiaryEntryDto(String username, ZonedDateTime createdAt, String body,
			Integer positivity) {
		super();
		this.username = username;
		this.createdAt = createdAt;
		this.body = body;
		this.positivity = positivity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPositivity() {
		return positivity;
	}

	public void setPositivity(Integer positivity) {
		this.positivity = positivity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "DiaryEntryDto [username=" + username + ", createdAt="
				+ createdAt + ", body=" + body + ", positivity=" + positivity
				+ "]";
	}

}
