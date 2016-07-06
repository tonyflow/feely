package base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.FeelyApplication;
import application.model.Feeler;
import application.model.Feeling;
import application.support.TransactionHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { FeelyApplication.class })
@TestPropertySource(properties = "spring.config.name=feely-application")
@ActiveProfiles("test")
public abstract class AbstractFeelyTest {

	@Autowired
	JdbcTemplate template;
	
	

	@Autowired
	public ObjectMapper mapper;

	@Before
	public void setUp() {
		TransactionHelper.truncate(template, Feeler.class, Feeling.class);
	}

	@After
	public void tearDown() {
//		TransactionHelper.truncate(template, Feeler.class, Feeling.class);
	}
}
