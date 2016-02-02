package application.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("live")
public class TransportClientConfiguration {

	@Autowired
	ElasticsearchConfigurationProperties properties;

	@SuppressWarnings("resource")
	@Bean
	public Client client() {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put(ElasticSettings.NUMBER_OF_REPLICAS, properties.getNumberOfReplicas())
				.put(ElasticSettings.NUMBER_OF_SHARDS, properties.getNumberOfShards())
				.put(ElasticSettings.CLUSTER_NAME, properties.getClusterName())
				.put(ElasticSettings.CLUSTER_SNIFF, properties.isClusterSniff())
				.build();
		InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(properties.getHost(), properties.getPort());
		TransportClient client = new TransportClient(settings).addTransportAddress(inetSocketTransportAddress);
		return client;
	}

}
