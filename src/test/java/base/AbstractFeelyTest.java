package base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import application.FeelyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FeelyApplication.class)
@TestPropertySource(properties = "spring.config.name=feely-application")
public abstract class AbstractFeelyTest {

}
