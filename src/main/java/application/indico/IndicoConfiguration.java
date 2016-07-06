package application.indico;

import io.indico.Indico;
import io.indico.api.utils.IndicoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndicoConfiguration {

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Value("${application.indico.apiKey}")
	private String apiKey;

	@Bean
	Indico getIndico() throws IndicoException {
		return new Indico(apiKey);
	}
}
