package application.api;

import application.api.dto.UserInfoDto;

public interface UserInfoService {

	UserInfoDto index();

	UserInfoDto get();

	UserInfoDto delete();

	UserInfoDto search();
}
