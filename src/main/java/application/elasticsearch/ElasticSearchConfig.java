package application.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class has dual purpose. Parsing configuration properties yaml and declaring
 * (configuring) the required beans for the elastic search integration with the
 * server
 * 
 * @author niko.strongioglou
 *
 */
@Configuration
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "application")
public class ElasticSearchConfig {

	private String clusterName;
	private String index;
	private String documentType;
	private String numberOfShards;
	private String numberOfReplicas;

	public String getNumberOfReplicas() {
		return numberOfReplicas;
	}

	public void setNumberOfReplicas(String numberOfReplicas) {
		this.numberOfReplicas = numberOfReplicas;
	}

	public String getNumberOfShards() {
		return numberOfShards;
	}

	public void setNumberOfShards(String numberOfShards) {
		this.numberOfShards = numberOfShards;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@Bean
	public Client client() {
		Node node = NodeBuilder
				.nodeBuilder()
				.settings(
						ImmutableSettings.settingsBuilder()
								.put("http.enabled", true)
								.put("number_of_shards", Integer.valueOf(getNumberOfShards()).intValue())
								.put("number_of_replicas", Integer.valueOf(getNumberOfReplicas()).intValue()))
				.clusterName(this.clusterName).node();
		return node.client();
	}

}
