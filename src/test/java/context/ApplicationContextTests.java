package context;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import application.FeelyApplication;
import base.AbstractFeelyTest;

public class ApplicationContextTests extends AbstractFeelyTest {

	@Autowired
	ApplicationContext context;

	@SuppressWarnings("deprecation")
	@Test
	public void testContext() throws Exception {
		Map<String, FeelyApplication> beansOfType = context.getBeansOfType(FeelyApplication.class);

		for (Map.Entry<String, FeelyApplication> entry : beansOfType.entrySet()) {
			System.out.println("Key :: " + entry.getKey());
		}

		// Assert.assertTrue(context.containsBean("feelyRepository"));

	}

}
