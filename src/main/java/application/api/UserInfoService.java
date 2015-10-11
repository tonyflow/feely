package application.api;

import application.api.dto.UserInfoDto;

public interface UserInfoService {

	UserInfoDto index(UserInfoDto userInfo);

	UserInfoDto get(Long id);

	UserInfoDto get(String id);

	/**
	 * Will delete user corrsponding to the specific
	 * 
	 * 
	 * @param documentId
	 * @return
	 */
	Boolean delete(String id);

	Boolean delete(Long id);

	UserInfoDto search(UserInfoDto userInfo);
}
