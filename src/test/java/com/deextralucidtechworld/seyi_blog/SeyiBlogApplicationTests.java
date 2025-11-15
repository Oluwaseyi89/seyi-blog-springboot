




package com.deextralucidtechworld.seyi_blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
class SeyiBlogApplicationTests {

	static {
		// Load .env and set as system properties for Spring Boot test context
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		setPropertyIfPresent(dotenv, "POSTGRES_DB");
		setPropertyIfPresent(dotenv, "POSTGRES_USER");
		setPropertyIfPresent(dotenv, "POSTGRES_PASSWORD");
	}

	private static void setPropertyIfPresent(Dotenv dotenv, String key) {
		if (System.getProperty(key) == null) {
			String value = dotenv.get(key);
			if (value != null) {
				System.setProperty(key, value);
			}
		}
	}

	@Test
	void contextLoads() {
	}

}
