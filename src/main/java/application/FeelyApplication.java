package application;

import java.time.Clock;
import java.time.ZoneId;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import application.elasticsearch.ElasticSettings;
import application.elasticsearch.ElasticsearchConfigurationProperties;

import com.google.common.collect.ImmutableMap;

/**
 * 
 * @author niko.strongioglou
 * 
 */

@SpringBootApplication
@ComponentScan(basePackages = "application")
@EnableConfigurationProperties(ElasticsearchConfigurationProperties.class)
public class FeelyApplication extends SpringBootServletInitializer {

	@Autowired
	ElasticsearchConfigurationProperties properties;

	Logger logger = LoggerFactory.getLogger(getClass());
	private static final Object CONFIG_NAME = "feely-application";

	public static void main(String[] args) {
		new SpringApplicationBuilder(FeelyApplication.class).properties(ImmutableMap.of("spring.config.name", CONFIG_NAME)).build().run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.properties(ImmutableMap.of("spring.config.name", CONFIG_NAME));
	}

	@Bean
	public ZoneId getTimeZone() {
		return ZoneId.systemDefault();
	}
	
	@Bean
	public Clock getClock(ZoneId zoneId){
		return Clock.system(zoneId);
	}
	
	@Bean
	MailSender mailSender(){
		JavaMailSenderImpl mailServer = new JavaMailSenderImpl();
		//should create an email server
		mailServer.setHost("mail.mymailserver.com");
		return mailServer;
	}

	@Bean
	@Profile("test")
	public Client localNodeClient() {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put(ElasticSettings.NUMBER_OF_REPLICAS, properties.getNumberOfReplicas())
				.put(ElasticSettings.NUMBER_OF_SHARDS, properties.getNumberOfShards())
				.put(ElasticSettings.CLUSTER_NAME, properties.getClusterName())
				.put(ElasticSettings.CLUSTER_SNIFF, properties.isClusterSniff())
				.build();
		Node node = NodeBuilder.nodeBuilder().clusterName(properties.getClusterName()).settings(settings).local(true).node();
		Client client = node.client();
		logger.info("Node client is :: " + System.identityHashCode(client));
		return client;
	}

}
