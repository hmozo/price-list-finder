package cucumber;

import com.indit.pricelist.contexts.shop.pricelists.application.exceptions.PriceListNotFoundException;
import com.indit.pricelist.contexts.shop.pricelists.controller.PriceListController;
import com.indit.pricelist.contexts.shop.pricelists.controller.dto.PriceListResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceListSteps extends SpringIntegrationTest {

    @Autowired
    private PriceListController priceListController;
    private Long productId;
    private int brandId;
    private List<LocalDateTime> parsedEffectiveDates;
    private List<ResponseEntity<PriceListResponse>> expectedResponseEntities;

    @Given("the effective dates are as follows:")
    public void setEffectiveDate(DataTable dataTable) {
        parsedEffectiveDates= new ArrayList<>();
        for (int row = 2; row < dataTable.height(); row++) {
            this.parsedEffectiveDates.add(LocalDateTime.parse(dataTable.cell(row, 0)));
        }
    }

    @And("the product ID is {long}")
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @And("the brand ID is {int}")
    public void setBrandId(int brandId) {
        // Set the brand ID for the price list request
        this.brandId = brandId;
    }

    @When("I request the price-tariff")
    public void requestPriceTariff() throws PriceListNotFoundException {
        expectedResponseEntities= new ArrayList<>();
        for (LocalDateTime effectiveDate:parsedEffectiveDates) {
            expectedResponseEntities.add(priceListController.getPriceListByDateProductBrand(effectiveDate, productId, brandId));
        }
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeOfTheResponseEntityShouldBe(int status) {
        for(var expectedResponseEntity:expectedResponseEntities) {
            assertEquals(expectedResponseEntity.getStatusCode().value(), status);
        }
    }

    @And("the priceListResponse should have the following details:")
    public void verifyPriceListResponseDetails(DataTable dataTable) {
        for (int row = 2; row < dataTable.height(); row++) {
            PriceListResponse expectedPriceListResponse = buildExpectedPriceListResponseFromDataTable(dataTable, row);

            assertEquals(expectedPriceListResponse.getProductId(), expectedResponseEntities.get(row-2).getBody().getProductId());
            assertEquals(expectedPriceListResponse.getBrandId(), expectedResponseEntities.get(row-2).getBody().getBrandId());
            assertEquals(expectedPriceListResponse.getPriceListId(), expectedResponseEntities.get(row-2).getBody().getPriceListId());
            assertEquals(expectedPriceListResponse.getPrice(), expectedResponseEntities.get(row-2).getBody().getPrice());
            assertEquals(expectedPriceListResponse.getCurrency(), expectedResponseEntities.get(row-2).getBody().getCurrency());
            assertEquals(expectedPriceListResponse.getStartDate(), expectedResponseEntities.get(row-2).getBody().getStartDate());
            assertEquals(expectedPriceListResponse.getEndDate(), expectedResponseEntities.get(row-2).getBody().getEndDate());
        }
    }

    private PriceListResponse buildExpectedPriceListResponseFromDataTable(DataTable dataTable, int row) {
        String productIdString = dataTable.cell(row, 0);
        String brandIdString = dataTable.cell(row, 1);
        String priceListIdString = dataTable.cell(row, 2);
        String priceString = dataTable.cell(row, 3);
        String currency = dataTable.cell(row, 4);
        String startDateString = dataTable.cell(row, 5);
        String endDateString = dataTable.cell(row, 6);

        var priceListResponse= new PriceListResponse();
        priceListResponse.setPriceListId(Long.parseLong(priceListIdString));
        priceListResponse.setBrandId(Integer.parseInt(brandIdString));
        priceListResponse.setProductId(Long.parseLong(productIdString));
        priceListResponse.setPrice(new BigDecimal(priceString));
        priceListResponse.setCurrency(currency);
        priceListResponse.setStartDate(LocalDateTime.parse(startDateString));
        priceListResponse.setEndDate(LocalDateTime.parse(endDateString));

        return priceListResponse;
    }
}
