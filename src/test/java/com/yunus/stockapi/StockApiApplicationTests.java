package com.yunus.stockapi;

import com.yunus.stockapi.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class StockApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private StockRepository stockRepository;

	@Test
	void contextLoads() {
	}

}
