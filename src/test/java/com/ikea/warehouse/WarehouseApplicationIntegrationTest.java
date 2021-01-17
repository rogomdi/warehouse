package com.ikea.warehouse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WarehouseApplicationIntegrationTest {

	@DisplayName("Tests if the whole context loads successfully")
	@Test
	void contextLoads() {
		assertTrue(true,"If the test reaches this statement, it means that the context has loaded successfully");
	}

}
