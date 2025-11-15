package com.deextralucidtechworld.seyi_blog;

import jakarta.annotation.Resource;

import com.deextralucidtechworld.seyi_blog.Controllers.StorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SeyiBlogApplication implements CommandLineRunner {

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		// Load .env file for local development (optional)
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// Set .env variables as system properties if not already set
		setPropertyIfPresent(dotenv, "POSTGRES_DB");
		setPropertyIfPresent(dotenv, "POSTGRES_USER");
		setPropertyIfPresent(dotenv, "POSTGRES_PASSWORD");

		// Log environment variables for debugging
		System.out.println("POSTGRES_DB=" + System.getProperty("POSTGRES_DB", dotenv.get("POSTGRES_DB", System.getenv("POSTGRES_DB"))));
		System.out.println("POSTGRES_USER=" + System.getProperty("POSTGRES_USER", dotenv.get("POSTGRES_USER", System.getenv("POSTGRES_USER"))));
		System.out.println("POSTGRES_PASSWORD=" + System.getProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD", System.getenv("POSTGRES_PASSWORD"))));

		SpringApplication.run(SeyiBlogApplication.class, args);
	}

	private static void setPropertyIfPresent(Dotenv dotenv, String key) {
		if (System.getProperty(key) == null) {
			String value = dotenv.get(key);
			if (value != null) {
				System.setProperty(key, value);
			}
		}
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}

}
