package application.elasticsearch;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class has dual purpose. Parsing configuration properties yaml and declaring
 * (configuring) the required beans for the elastic search integration with the
 * server
 * 
 * @author niko.strongioglou
 *
 */
@ConfigurationProperties(locations = "classpath:feely-application.yml", ignoreUnknownFields = false, prefix = "application.elasticsearch")
public class ElasticsearchConfigurationProperties {

	@NotNull
	private String host;

	@NotNull
	private Integer port;

	@NotNull
	private String clusterName;

	@NotNull
	private String userIndex;
	
	@NotNull
	private String diaryIndex;

	@NotNull
	private String documentType;

	@NotNull
	private String numberOfShards;

	@NotNull
	private String numberOfReplicas;

	@NotNull
	private boolean clusterSniff;

	public boolean isClusterSniff() {
		return clusterSniff;
	}

	public void setClusterSniff(boolean clusterSniff) {
		this.clusterSniff = clusterSniff;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

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

	
	
	public String getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(String userIndex) {
		this.userIndex = userIndex;
	}

	public String getDiaryIndex() {
		return diaryIndex;
	}

	public void setDiaryIndex(String diaryIndex) {
		this.diaryIndex = diaryIndex;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

}
