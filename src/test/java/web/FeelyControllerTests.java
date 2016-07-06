package web;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import application.api.dto.FeelerDto;
import application.api.dto.FeelingDto;
import application.dao.FeelingRepository;
import application.model.Feeling;
import base.AbstractFeelyTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
public class FeelyControllerTests extends AbstractFeelyTest {

	MockMvc mockMvc;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ObjectMapper mapper;

	@Autowired
	WebApplicationContext context;

	@Autowired
	FeelingRepository feelingRepository;

	@Override
	public void setUp() {

		super.setUp();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	@Test
	public void testListFeelingNormal() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/feely/list/feeling").param("id",
						"1")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testAddFeelingNormal() throws Exception {

		String feelingString = mapper.writeValueAsString(new FeelingDto(
				"sadness", "unpleasant", 12));
		logger.info("Serialized feeling : " + feelingString);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/feely/add/feeling")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(feelingString))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());

		Feeling f = feelingRepository.findByName("sadness");

		Assert.assertEquals("unpleasant", f.getTimbre());
	}

	@Test
	public void testRegistration() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/feely/register")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(
						mapper.writeValueAsString(new FeelerDto("nikos",
								"strogioglou", "nikos.strongioglou@gmail.com",
								"male", 29, "tonyflow", "lalakoko", null, null,
								null))));
		
		System.out.println("Waiting for email to be sent...");
	}

}
