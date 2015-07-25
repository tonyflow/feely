package application.support;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import application.model.PreviousFeelings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class PreviousFeelingsConverter implements AttributeConverter<PreviousFeelings, String> {

	private final static ObjectMapper MAPPER = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).enable(
			SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String convertToDatabaseColumn(PreviousFeelings previousFeelings) {
		try {
			return MAPPER.writeValueAsString(previousFeelings);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.info("Could not serialize required object");
			return null;
		}
	}

	@Override
	public PreviousFeelings convertToEntityAttribute(String previousFeelings) {
		try {
			return MAPPER.readValue(previousFeelings, PreviousFeelings.class);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Could not serialize required object");
			return null;
		}
	}
}
