package application.support;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Assert;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionHelper {

	public static void truncate(JdbcTemplate template, Class<?>... classes) {

		for (Class<?> c : classes) {
			Assert.assertNotNull("No Jpa entity found for specified class", c.getAnnotation(Entity.class));
			Table t = c.getAnnotation(Table.class);

			String schema = t.schema();
			String table = t.name();

			template.execute("TRUNCATE TABLE " + schema + "." + table + " RESTART IDENTITY CASCADE");
		}

	}
}
