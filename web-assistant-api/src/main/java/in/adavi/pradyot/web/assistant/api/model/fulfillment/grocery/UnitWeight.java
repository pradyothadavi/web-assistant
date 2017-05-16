package in.adavi.pradyot.web.assistant.api.model.fulfillment.grocery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnitWeight {
  
  @JsonProperty("unit")
  private String unit;
  
  @JsonProperty("amount")
  private Double quantity;
  
  @JsonIgnore
  public String getUnit() {
    return unit;
  }
  
  public void setUnit(String unit) {
    this.unit = unit;
  }
  
  @JsonIgnore
  public Double getQuantity() {
    return quantity;
  }
  
  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }
  
  @Override
  public String toString() {
    return "UnitWeight{" + "unit='" + unit + '\'' + ", quantity=" + quantity + '}';
  }
}
