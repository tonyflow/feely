package dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.dao.FeelingRepository;
import application.model.Feeling;
import base.AbstractFeelyTest;

public class FeelingRepositoryTests extends AbstractFeelyTest {

	@Autowired
	FeelingRepository repo;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Before
	public void setUp() {
		repo.deleteAll();
	}

	@Test
	public void testUpdate() throws Exception {
		Feeling feeling = new Feeling("Sadness", "Unpleasant", 0);
		feeling.setId(1);
		Feeling save = repo.save(feeling);

		assertEquals("Sadness", save.getName());
		assertEquals("Unpleasant", save.getTimbre());
		assertEquals(Integer.valueOf(0), save.getPotency());
	}
	
	@Test
	public void testPrintJson() throws Exception {
		logger.info(mapper.writeValueAsString(new Feeling("sadness", "unpleasant", 4)));
	}

}
