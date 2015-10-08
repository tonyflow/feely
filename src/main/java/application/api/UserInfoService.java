package application.api;

import application.api.dto.UserInfoDto;

public interface UserInfoService {

	UserInfoDto index(UserInfoDto userInfo);

	UserInfoDto get(UserInfoDto userInfo);

	/**
	 * Will delete user corrsponding to the specific
	 * 
	 * 
	 * @param documentId
	 * @return
	 */
	UserInfoDto delete(Long documentId);

	UserInfoDto search(UserInfoDto userInfo);
}
