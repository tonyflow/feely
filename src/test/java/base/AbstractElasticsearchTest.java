package base;

import javax.annotation.PreDestroy;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import application.elasticsearch.ElasticsearchConfigurationProperties;

public abstract class AbstractElasticsearchTest extends AbstractFeelyTest {

	@Autowired
	ElasticsearchConfigurationProperties properties;

	@Autowired
	Client client;

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Embedded client used for integration and unit testing
	 * 
	 * @return Client
	 */
	@Bean
	Client nodeClient() {
		Node node = NodeBuilder.nodeBuilder().clusterName(properties.getClusterName()).node();
		Client client = node.client();
		return client;
	}

	@PreDestroy
	public void closeClient() {
		this.nodeClient().close();
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();
		BulkRequestBuilder bulk = client.prepareBulk();
		bulk.add(client.prepareIndex(properties.getIndex(), properties.getDocumentType()).setSource(buildStringFromResourceFile()));
		BulkResponse actionGet = bulk.execute().actionGet();
		if (actionGet.hasFailures()) {
			logger.error("Could not complete bulk load from accounts.json");
		}
	}

	private String buildStringFromResourceFile() {
		return null;
	}
}
