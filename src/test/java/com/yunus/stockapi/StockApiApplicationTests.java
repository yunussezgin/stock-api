package com.yunus.stockapi;

import com.yunus.stockapi.entity.Stock;
import com.yunus.stockapi.entity.StockUpdate;
import com.yunus.stockapi.util.TestConstants;
import com.yunus.stockapi.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
						.param("size", "5")
						.param("sort", "name,asc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(5)));
	}

	@Test
	void givenStock_whenSaveStock_thenReturnStockSuccessfully() throws Exception {
		Stock stock = TestUtils.entityFromJsonFile(Stock.class, TestConstants.JSON_STOCK_FULL_PAYLOAD_CREATE_STOCK_SUCCESSFULLY_01);

		mvc.perform(post(TestConstants.STOCK_API_BASE_PATH)
						.header("Content-Type", TestConstants.JSON_CONTENT_TYPE_HEADER)
						.content(TestUtils.asJsonString(stock)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.id", is(notNullValue())))
				.andExpect(jsonPath("$.productCode", is("B09KCNSQYB")))
				.andExpect(jsonPath("$.name", is("Logitech MX Master 3S")))
				.andExpect(jsonPath("$.lastUpdate", is(notNullValue())))
				.andExpect(jsonPath("$.currentPrice", is(96.00)));
	}

	@Test
	void givenStockId_whenRetrieveStock_thenReturnStockSuccessfully() throws Exception {
		mvc.perform(get(TestConstants.STOCK_API_BASE_PATH + "/19a4af8c-8292-4798-b4f2-e769bb1b491c"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.id", is("19a4af8c-8292-4798-b4f2-e769bb1b491c")))
				.andExpect(jsonPath("$.productCode", is("B09KCNSQYN")))
				.andExpect(jsonPath("$.name", is("BOOX Note Air2 Plus")))
				.andExpect(jsonPath("$.lastUpdate", is("18-12-2022 08:02:05")))
				.andExpect(jsonPath("$.currentPrice", is(518.19)));
	}

	@Test
	void givenStock_whenUpdateStock_thenReturnUpdatedStockSuccessfully() throws Exception {
		StockUpdate stock = TestUtils.entityFromJsonFile(StockUpdate.class, TestConstants.JSON_STOCK_PAYLOAD_UPDATE_STOCK_SUCCESSFULLY_01);

		mvc.perform(patch(TestConstants.STOCK_API_BASE_PATH + "/1d06b6f0-4a0b-4c18-83a0-d96be6beea88")
						.header("Content-Type", TestConstants.JSON_CONTENT_TYPE_HEADER)
						.content(TestUtils.asJsonString(stock)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("1d06b6f0-4a0b-4c18-83a0-d96be6beea88")))
				.andExpect(jsonPath("$.productCode", is("B09KCNSQYH")))
				.andExpect(jsonPath("$.name", is("Russell Hobbs Cook Egg Cooker")))
				.andExpect(jsonPath("$.currentPrice", is(100.00)));
	}

	@Test
	void givenStock_whenUpdateStockWithDuplicateProductCode_thenReturnUpdatedStockUnSuccessfully() throws Exception {
		StockUpdate stock = TestUtils.entityFromJsonFile(StockUpdate.class, TestConstants.JSON_STOCK_PAYLOAD_UPDATE_STOCK_UNSUCCESSFULLY_01);

		mvc.perform(patch(TestConstants.STOCK_API_BASE_PATH + "/1d06b6f0-4a0b-4c18-83a0-d96be6beea88")
						.content(TestUtils.asJsonString(stock)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void givenStockId_whenDeleteStock_thenDeleteStockSuccessfully() throws Exception {
		mvc.perform(delete(TestConstants.STOCK_API_BASE_PATH + "/096e8c09-b4eb-4c5d-b4b5-d60595408d35"))
				.andExpect(status().isNoContent());
	}

	@Test
	void givenNonExistStockId_whenRetrieveStock_thenReturnNotFoundException() throws Exception {
		mvc.perform(get(TestConstants.STOCK_API_BASE_PATH + "/xxx"))
				.andExpect(status().isNotFound());
	}

	@Test
	void givenNonExistStockId_whenDeleteStock_thenReturnReturnNotFoundException() throws Exception {
		mvc.perform(delete(TestConstants.STOCK_API_BASE_PATH + "/xxx"))
				.andExpect(status().isNotFound());
	}
}
