package dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import application.dao.FeelerRepository;
import application.model.Feeler;
import application.model.PreviousFeelings;
import base.AbstractFeelyTest;

import com.google.common.collect.ImmutableList;

public class FeelerRepositoryTests extends AbstractFeelyTest {

	@Autowired
	FeelerRepository repo;

	@Test
	public void testFeeler() throws Exception {

		Feeler feeler = new Feeler(1l, "nikos", "strongioglou", "petriani@gmail.com", "male", 29, "username", null, null, null, new PreviousFeelings(ImmutableList.of("happy", "gorgeous")));
		feeler = repo.save(feeler);
		assertEquals("username", feeler.getUsername());
	}

}
