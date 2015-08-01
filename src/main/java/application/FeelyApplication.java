package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.google.common.collect.ImmutableMap;

/**
 * 
 * @author niko.strongioglou
 * 
 */

@SpringBootApplication
@ComponentScan(basePackages = "application")
public class FeelyApplication extends SpringBootServletInitializer {

	Logger logger = LoggerFactory.getLogger(getClass());
	private static final Object CONFIG_NAME = "feely-application";

	public static void main(String[] args) {
		new SpringApplicationBuilder(FeelyApplication.class).properties(ImmutableMap.of("spring.config.name", CONFIG_NAME)).build().run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.properties(ImmutableMap.of("spring.config.name", CONFIG_NAME));
	}

}
