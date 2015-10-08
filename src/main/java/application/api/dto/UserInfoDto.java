package application.api.dto;

import java.io.Serializable;

/**
 * The class is destinded to incorporate Elastic Search opearation
 * 
 * @author niko.strongioglou
 *
 */
public class UserInfoDto implements Serializable {

	private static final long serialVersionUID = -2663588817541064476L;
	private Long id;
	private String username;
	private String password;
	private String email;
	private String location;
	private String occupation;

	public UserInfoDto() {
	}

	public UserInfoDto(String username, String password, String email, String location, String occupation, Long id) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.location = location;
		this.occupation = occupation;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", location=" + location + ", occupation="
				+ occupation + "]";
	}

}
