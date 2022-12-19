package com.yunus.stockapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.stockapi.entity.Stock;
import com.yunus.stockapi.entity.StockUpdate;
import com.yunus.stockapi.repository.StockRepository;
import com.yunus.stockapi.util.JsonMergePatcher;
import com.yunus.stockapi.util.TestConstants;
import com.yunus.stockapi.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class StockServiceTests {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private JsonMergePatcher jsonMergePatcher;

    @Captor
    ArgumentCaptor<Stock> stockCaptor;

    @Test
    public void givenPageParams_whenListStock_thenReturnStocksSuccessfully() throws Exception {
        List<Stock> stockList = TestUtils.entityListFromJsonFile(Stock.class, TestConstants.JSON_STOCK_FULL_RESPONSE_LIST_SUCCESSFULLY_01);

        // given
        given(stockRepository.findAll(PageRequest.of(0,2, Sort.by("name,asc")))).willReturn(new PageImpl<>(stockList));

        // when
        List<Stock> stock = stockService.listStock(PageRequest.of(0,2, Sort.by("name,asc"))).getContent();

        // then
        assertThat(stock.get(0).getId()).isEqualTo("e6a15c12-d8a5-4e31-ad16-a2020e8f9d56");
        assertThat(stock.get(0).getProductCode()).isEqualTo("B09KCNSQYS");
        assertThat(stock.get(0).getName()).isEqualTo("Philips Azur Steam iron");
        assertThat(stock.get(0).getCurrentPrice()).isEqualByComparingTo("53.56");
        assertThat(stock.get(0).getLastUpdate()).isEqualTo("2022-12-18T10:15:30");
        assertThat(stock.get(1).getId()).isEqualTo("1d06b6f0-4a0b-4c18-83a0-d96be6beea88");
        assertThat(stock.get(1).getProductCode()).isEqualTo("B09KCNSQYL");
        assertThat(stock.get(1).getName()).isEqualTo("Philips Airfryer");
        assertThat(stock.get(1).getCurrentPrice()).isEqualByComparingTo("82.78");
        assertThat(stock.get(1).getLastUpdate()).isEqualTo("2022-12-18T10:15:30");
    }

    @Test
    void givenStock_whenSaveStock_thenReturnStockSuccessfully() throws Exception {
        Stock stock = TestUtils.entityFromJsonFile(Stock.class, TestConstants.JSON_STOCK_FULL_RESPONSE_GET_SUCCESSFULLY_01);

        // given
        given(stockRepository.save(any())).willReturn(stock);

        // when
        Stock stockSaved = stockService.createStock(stock);

        // then
        assertThat(stockSaved.getId()).isEqualTo("e6a15c12-d8a5-4e31-ad16-a2020e8f9d56");
        assertThat(stockSaved.getProductCode()).isEqualTo("B09KCNSQYS");
        assertThat(stockSaved.getName()).isEqualTo("Philips Azur Steam iron");
        assertThat(stockSaved.getCurrentPrice()).isEqualByComparingTo("53.56");
        assertThat(stockSaved.getLastUpdate()).isEqualTo("2022-12-18T10:15:30");
    }

    @Test
    void givenStock_whenUpdateStock_thenReturnUpdatedStockSuccessfully() throws Exception {
        Stock stock = TestUtils.entityFromJsonFile(Stock.class, TestConstants.JSON_STOCK_FULL_RESPONSE_GET_SUCCESSFULLY_01);

        // given
        given(stockRepository.findById(stock.getId())).willReturn(Optional.of(stock));
        stock.setName("Updated Stock");
        given(stockRepository.save(any())).willReturn(stock);

        // when
        StockUpdate stockUpdate = new StockUpdate();
        stockUpdate.setName("Updated Stock");
        Stock stockUpdated = stockService.updateStock(stock.getId(), stockUpdate);

        // then
        assertThat(stockUpdated.getId()).isEqualTo("e6a15c12-d8a5-4e31-ad16-a2020e8f9d56");
        assertThat(stockUpdated.getProductCode()).isEqualTo("B09KCNSQYS");
        assertThat(stockUpdated.getName()).isEqualTo("Updated Stock");
        assertThat(stockUpdated.getCurrentPrice()).isEqualByComparingTo("53.56");
        assertThat(stockUpdated.getLastUpdate()).isEqualTo("2022-12-18T10:15:30");
    }

    @Test
    void givenStockId_whenRetrieveStock_thenReturnStockSuccessfully() throws Exception {
        Stock stock = TestUtils.entityFromJsonFile(Stock.class, TestConstants.JSON_STOCK_FULL_RESPONSE_GET_SUCCESSFULLY_01);

        // given
        given(stockRepository.findById(stock.getId())).willReturn(Optional.of(stock));

        // when
        Stock stockFound = stockService.retrieveStock(stock.getId());

        // then
        assertThat(stockFound.getId()).isEqualTo("e6a15c12-d8a5-4e31-ad16-a2020e8f9d56");
        assertThat(stockFound.getProductCode()).isEqualTo("B09KCNSQYS");
        assertThat(stockFound.getName()).isEqualTo("Philips Azur Steam iron");
        assertThat(stockFound.getCurrentPrice()).isEqualByComparingTo("53.56");
        assertThat(stockFound.getLastUpdate()).isEqualTo("2022-12-18T10:15:30");
    }

    @Test
    void givenStockId_whenDeleteStock_thenDeleteStockSuccessfully() throws Exception {
        Stock stock = TestUtils.entityFromJsonFile(Stock.class, TestConstants.JSON_STOCK_FULL_RESPONSE_GET_SUCCESSFULLY_01);

        // given
        given(stockRepository.findById(stock.getId())).willReturn(Optional.of(stock));

        // when
        stockService.deleteStock(stock.getId());
        Mockito.verify(stockRepository).delete(stockCaptor.capture());
        Stock stockDeleted = stockCaptor.getValue();

        // then
        assertThat(stockDeleted.getId()).isEqualTo("e6a15c12-d8a5-4e31-ad16-a2020e8f9d56");
    }
}
