package base;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.indices.IndexMissingException;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import application.elasticsearch.ElasticsearchConfigurationProperties;
import application.service.UserInfoServiceImpl;

public abstract class AbstractElasticsearchTest extends AbstractFeelyTest {

	@Autowired
	ElasticsearchConfigurationProperties properties;

	@Autowired
	Client client;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Before
	public void setUp() {
		super.setUp();

		// client.admin().indices().delete(new
		// DeleteIndexRequest(properties.getIndex())).actionGet();

		client.admin().indices().prepareCreate(properties.getIndex()).get();
		// BulkRequestBuilder bulk = client.prepareBulk();
		// bulk.add(client.prepareIndex(properties.getIndex(),
		// properties.getDocumentType()).setSource(buildStringFromResourceFile()));
		// BulkResponse actionGet = bulk.execute().actionGet();
		// if (actionGet.hasFailures()) {
		// logger.error("Could not complete bulk load from accounts.json");
		// }
	}

	@Override
	@After
	public void tearDown() {
		try {
			client.admin().indices().delete(new DeleteIndexRequest(properties.getIndex())).actionGet();
			logger.info("Delete index " + properties.getIndex());
			// Reset index count due to the static nature of the variable
			// FIXME should not behave like that: Find another way to assign ids
			// to documents
			UserInfoServiceImpl.setIndexCnt(1l);
		} catch (IndexMissingException e) {
			logger.error(e.index().toString());
		}

	}

	private String buildStringFromResourceFile() {
		return null;
	}
}
