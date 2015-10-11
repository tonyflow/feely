package application.service;

import java.io.IOException;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import application.api.UserInfoService;
import application.api.dto.UserInfoDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserInfoServiceImpl implements UserInfoService {

	@Value("${application.elasticsearch.index}")
	private String index;

	@Value("${application.elasticsearch.document-type}")
	private String documentType;

	private static Long indexCnt = 1l;

	public static Long getIndexCnt() {
		return indexCnt;
	}

	public static void setIndexCnt(Long indexCnt) {
		UserInfoServiceImpl.indexCnt = indexCnt;
	}

	@Autowired
	Client elasticSearchClient;

	@Autowired
	ObjectMapper mapper;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserInfoDto index(UserInfoDto userInfo) {

		logger.info("Using " + System.identityHashCode(elasticSearchClient) + " client");
		logger.info("Index count :: " + this.indexCnt);

		try {
			IndexResponse actionGet = elasticSearchClient.prepareIndex(index, documentType, String.valueOf(indexCnt.longValue()))
					.setSource(mapper.writeValueAsString(userInfo))
					.execute()
					.actionGet();
			if (actionGet.isCreated()) {
				logger.info("Created new document : " + userInfo.toString() + " with id " + actionGet.getId() + " in index " + actionGet.getIndex());
				indexCnt++;
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
	public UserInfoDto get(String id) {

		logger.info("Using " + System.identityHashCode(elasticSearchClient) + " client");

		GetResponse actionGet = elasticSearchClient.prepareGet(index, documentType, id.toString()).execute().actionGet();
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
	public Boolean delete(String id) {

		logger.info("Using " + System.identityHashCode(elasticSearchClient) + " client");

		DeleteResponse delete = elasticSearchClient.prepareDelete(index, documentType, id).execute().actionGet();
		if (delete.isFound()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public UserInfoDto search(UserInfoDto userInfo) {

		logger.info("Using " + System.identityHashCode(elasticSearchClient) + " client");

		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Multiple signatures for methods get and delete
	 */

	@Override
	public UserInfoDto get(Long id) {
		return this.get(String.valueOf(id));
	}

	@Override
	public Boolean delete(Long id) {
		return this.delete(String.valueOf(id));
	}

}
