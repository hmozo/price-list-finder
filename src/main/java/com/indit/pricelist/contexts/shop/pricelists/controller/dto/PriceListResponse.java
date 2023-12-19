package com.indit.pricelist.contexts.shop.pricelists.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * PriceListResponse
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-18T16:25:31.523601600+01:00[Europe/Paris]")
public class PriceListResponse {

  private Long productId;

  private Integer brandId;

  private Long priceListId;

  private BigDecimal price;

  private String currency;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime endDate;

  public PriceListResponse productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
  */
  
  @Schema(name = "productId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productId")
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public PriceListResponse brandId(Integer brandId) {
    this.brandId = brandId;
    return this;
  }

  /**
   * Get brandId
   * @return brandId
  */
  
  @Schema(name = "brandId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("brandId")
  public Integer getBrandId() {
    return brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

  public PriceListResponse priceListId(Long priceListId) {
    this.priceListId = priceListId;
    return this;
  }

  /**
   * Get priceListId
   * @return priceListId
  */
  
  @Schema(name = "priceListId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priceListId")
  public Long getPriceListId() {
    return priceListId;
  }

  public void setPriceListId(Long priceListId) {
    this.priceListId = priceListId;
  }

  public PriceListResponse price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @Valid 
  @Schema(name = "price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public PriceListResponse currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  */
  
  @Schema(name = "currency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public PriceListResponse startDate(LocalDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  */
  @Valid 
  @Schema(name = "startDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startDate")
  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public PriceListResponse endDate(LocalDateTime endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
  */
  @Valid 
  @Schema(name = "endDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("endDate")
  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriceListResponse priceListResponse = (PriceListResponse) o;
    return Objects.equals(this.productId, priceListResponse.productId) &&
        Objects.equals(this.brandId, priceListResponse.brandId) &&
        Objects.equals(this.priceListId, priceListResponse.priceListId) &&
        Objects.equals(this.price, priceListResponse.price) &&
        Objects.equals(this.currency, priceListResponse.currency) &&
        Objects.equals(this.startDate, priceListResponse.startDate) &&
        Objects.equals(this.endDate, priceListResponse.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, brandId, priceListId, price, currency, startDate, endDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PriceListResponse {\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    brandId: ").append(toIndentedString(brandId)).append("\n");
    sb.append("    priceListId: ").append(toIndentedString(priceListId)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

