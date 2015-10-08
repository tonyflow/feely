package application.service;

import java.io.IOException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import application.api.UserInfoService;
import application.api.dto.UserInfoDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInfoServiceImpl implements UserInfoService {

	@Value("${application.elasticsearch.index}")
	private String index;

	@Value("${application.elasticsearch.document-type}")
	private String documentType;

	@Autowired
	Client elasticSearchClient;

	@Autowired
	ObjectMapper mapper;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserInfoDto index(UserInfoDto userInfo) {

		try {
			IndexResponse actionGet = elasticSearchClient.prepareIndex(index, documentType)
					.setSource(mapper.writeValueAsString(userInfo))
					.execute()
					.actionGet();
			if (actionGet.isCreated()) {
				logger.info("Created new document : " + userInfo.toString() + " with id " + actionGet.getId() + " in index " + actionGet.getIndex());
			}
			else {
				logger.info("Document : " + userInfo.toString() + " was updated");
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserInfoDto get(UserInfoDto userInfo) {
		GetResponse actionGet = elasticSearchClient.prepareGet(index, documentType, userInfo.getId().toString()).execute().actionGet();
		String document = actionGet.getSourceAsString();

		UserInfoDto user = null;
		if (document != null) {
			try {
				user = mapper.readValue(document, UserInfoDto.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public UserInfoDto delete(Long documentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoDto search(UserInfoDto userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
