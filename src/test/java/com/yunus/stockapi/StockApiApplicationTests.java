package com.yunus.stockapi;

import com.yunus.stockapi.entity.Stock;
import com.yunus.stockapi.repository.StockRepository;
import com.yunus.stockapi.util.TestConstants;
import com.yunus.stockapi.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class StockApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void givenPageParams_whenListStock_thenReturnStocksSuccessfully() throws Exception {
		mvc.perform(get(TestConstants.STOCK_API_BASE_PATH)
						.param("page", "0")
						.param("size", "5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()));
	}

	@Test
	void givenStock_whenSaveStock_thenReturnStockSuccessfully() throws Exception {
		Stock stock = TestUtils.entityFromJsonFile(Stock.class, TestConstants.JSON_STOCK_FULL_PAYLOAD_CREATE_STOCK_SUCCESSFULLY_01);

		mvc.perform(post(TestConstants.STOCK_API_BASE_PATH)
						.content(TestUtils.asJsonString(stock)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.productCode", is("B09KCNSQYN")));
	}

	@Test
	void givenStockId_whenRetrieveStock_thenReturnStockSuccessfully() throws Exception {
		mvc.perform(get(TestConstants.STOCK_API_BASE_PATH + "/19a4af8c-8292-4798-b4f2-e769bb1b491c"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()));
	}

	@Test
	void givenStock_whenUpdateStock_thenReturnUpdatedStockSuccessfully() throws Exception {
		Stock stock = TestUtils.entityFromJsonFile(Stock.class, TestConstants.JSON_STOCK_PAYLOAD_UPDATE_STOCK_SUCCESSFULLY_01);

		mvc.perform(patch(TestConstants.STOCK_API_BASE_PATH + "/1")
						.content(TestUtils.asJsonString(stock)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.currentPrice", is(100.00)));
	}

	@Test
	void givenStockId_whenDeleteStock_theDeleteStockSuccessfully() throws Exception {
		mvc.perform(delete(TestConstants.STOCK_API_BASE_PATH + "/1"))
				.andExpect(status().isNoContent());
	}

}
