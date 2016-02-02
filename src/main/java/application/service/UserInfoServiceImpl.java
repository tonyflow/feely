package application.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import application.api.UserInfoService;
import application.api.dto.UserInfoDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;

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

	/**
	 * merge non null values of updated user with existing user values. Id
	 * should be provided.
	 * 
	 * @param userInfo
	 * @return
	 */
	@Override
	public UserInfoDto update(UserInfoDto userInfo) {
		UserInfoDto update = null;
		try {
			UpdateRequest docUpdate = constructUpdateRequest(userInfo);
			UpdateResponse response = elasticSearchClient.update(docUpdate).get();
			Map<String, Object> updatesMap = response.getGetResult().getSource();
			if (updatesMap != null) {
				update = new UserInfoDto(updatesMap.get("username").toString(), updatesMap.get("password").toString(), updatesMap.get("email")
						.toString(), updatesMap
						.get("location").toString(), updatesMap.get("occupation").toString(), (Long) updatesMap.get("id"));
				logger.info("Succesfully updated document --> " + update.toString());
				return update;
			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return update;
	}

	@Override
	public UserInfoDto updateByScript() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoDto upsert(UserInfoDto userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	private UpdateRequest constructUpdateRequest(UserInfoDto userInfo) throws IOException {
		XContentBuilder updates = XContentFactory.jsonBuilder().startObject();
		if (userInfo.getEmail() != null) {
			updates.field("email", userInfo.getEmail());
		}
		if (userInfo.getLocation() != null) {
			updates.field("location", userInfo.getLocation());
		}
		if (userInfo.getOccupation() != null) {
			updates.field("occupation", userInfo.getOccupation());
		}
		if (userInfo.getPassword() != null) {
			updates.field("password", userInfo.getPassword());
		}
		if (userInfo.getUsername() != null) {
			updates.field("username", userInfo.getUsername());
		}
		return new UpdateRequest(index, documentType, userInfo.getId().toString()).doc(updates.endObject());
	}

	@Override
	public Boolean bulk(List<UserInfoDto> usersList) {
		BulkProcessor processor = BulkProcessor.builder(elasticSearchClient, new BulkProcessor.Listener() {

			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
				// TODO Auto-generated method stub

			}
		}).setBulkActions(10000)
				.setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
				.setConcurrentRequests(1)
				.setFlushInterval(TimeValue.timeValueMillis(5000l))
				.build();

		for (UserInfoDto user : usersList) {

		}
		return null;
	}

	public String readFile(String fileName) {
		InputStream is = getClass().getResourceAsStream(fileName);
		if (is != null) {
			try {
				IOUtils.toString(is, Charsets.UTF_8);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		else {
			logger.error("Could open file stream");
		}
		return null;
	}

}
