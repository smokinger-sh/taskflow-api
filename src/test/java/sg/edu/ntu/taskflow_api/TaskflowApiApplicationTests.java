package sg.edu.ntu.taskflow_api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:taskflow-test",
		"spring.datasource.username=sa",
		"spring.datasource.******spring.ai.openai.api-key=test-placeholder"
})
class TaskflowApiApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		try (var connection = dataSource.getConnection()) {
			assertEquals("H2", connection.getMetaData().getDatabaseProductName());
		}
	}

}
