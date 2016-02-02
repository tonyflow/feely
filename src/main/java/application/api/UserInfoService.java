package application.api;

import java.util.List;

import application.api.dto.UserInfoDto;

public interface UserInfoService {

	UserInfoDto index(UserInfoDto userInfo);

	UserInfoDto get(Long id);

	UserInfoDto get(String id);

	/**
	 * Will delete user corresponding to the specific
	 * 
	 * 
	 * @param documentId
	 * @return
	 */
	Boolean delete(String id);

	Boolean delete(Long id);

	UserInfoDto update(UserInfoDto userInfo);

	/**
	 * Previous ES versions had a vulnerability in the Grooovy scripting engine.
	 * The vulnerability allows an attacker to construct Groovy scripts that
	 * escape the sandbox and execute shell commands as the user running the ES
	 * VM. script.groovy.sandbox.enabled is set by default to false turning ff
	 * Groove sandbox , thus preventing Groovy scripts from being accepted as
	 * part of a request or retrieved form the special .scripts index. We can
	 * still use Groovy scripts stored in files in the /config/scripts directory
	 * of every node.
	 * 
	 * @return
	 */
	UserInfoDto updateByScript();

	UserInfoDto upsert(UserInfoDto userInfo);

	UserInfoDto search(UserInfoDto userInfo);

	Boolean bulk(List<UserInfoDto> usersList);
}
