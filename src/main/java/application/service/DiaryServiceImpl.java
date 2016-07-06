package application.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeIndicesAdminClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.api.dto.DiaryEntryDto;
import application.api.dto.DiaryService;
import application.api.dto.UserInfoDto;

@Component
public class DiaryServiceImpl implements DiaryService {

	@Value("${application.elasticsearch.diary-index}")
	private String index;

	@Value("${application.elasticsearch.document-type}")
	private String documentType;

	@Autowired
	Client elasticSearchClient;

//	@Autowired
//	NodeIndicesAdminClient nodeIndicesAdminClient;

	@Autowired
	ObjectMapper mapper;

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public DiaryEntryDto index(DiaryEntryDto diaryEntry) {

		String[] indices = elasticSearchClient
				.admin()
				.indices()
				.getIndex(
						new GetIndexRequest().indices(diaryEntry.getUsername()))
				.actionGet().getIndices();
		if (indices.length == 0) {
			elasticSearchClient.admin().indices()
					.create(new CreateIndexRequest(diaryEntry.getUsername())).actionGet();
		}

		IndexResponse actionGet = elasticSearchClient
				.prepareIndex(
						diaryEntry.getUsername(), // index
						documentType, // document type
						String.valueOf(diaryEntry.getCreatedAt().toInstant()
								.toEpochMilli())) //
				.setSource(diaryEntry.getBody()).execute().actionGet();

		if (actionGet.isCreated()) {
			LOGGER.info("Created new document : " + diaryEntry.toString()
					+ " with id " + actionGet.getId() + " in index "
					+ actionGet.getIndex());
		} else {
			LOGGER.info("Document : " + diaryEntry.toString() + " was updated");
		}
		return null;
	}

	@Override
	public DiaryEntryDto get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaryEntryDto get(String username) {

		GetResponse actionGet = elasticSearchClient
				.prepareGet(username, documentType, null).execute().actionGet();
		String document = actionGet.getSourceAsString();

		DiaryEntryDto entry = null;
		if (document != null) {
			try {
				entry = mapper.readValue(document, DiaryEntryDto.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return entry;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaryEntryDto update(DiaryEntryDto t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaryEntryDto updateByScript() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaryEntryDto upsert(DiaryEntryDto t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaryEntryDto search(DiaryEntryDto t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean bulk(List<DiaryEntryDto> t) {
		// TODO Auto-generated method stub
		return null;
	}

}
