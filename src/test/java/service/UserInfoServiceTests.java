package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import application.api.UserInfoService;
import application.api.dto.UserInfoDto;
import base.AbstractElasticsearchTest;

public class UserInfoServiceTests extends AbstractElasticsearchTest {

	@Autowired
	UserInfoService service;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		UserInfoDto user = new UserInfoDto();
		user.setEmail("petriani@gmail.com");
		user.setLocation("Pireaus");
		user.setOccupation("Software Engineer");
		user.setPassword("pass");
		user.setUsername("tonyflow");
		service.index(user);
	}

	@Test
	public void testIndex() throws Exception {

		System.out.println("Indexed document");
	}

	@Test
	public void testGet() throws Exception {

		UserInfoDto get = service.get("1");

		Assert.assertEquals("tonyflow", get.getUsername());
	}

	@Test
	public void testDelete() throws Exception {
		Assert.assertTrue(service.delete("1"));

	}

	@Test
	public void testUpdate() throws Exception {

	}

}
