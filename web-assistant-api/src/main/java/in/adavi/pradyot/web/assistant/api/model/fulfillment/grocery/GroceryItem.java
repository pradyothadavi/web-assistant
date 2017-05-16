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
public class GroceryItem {
  
  @JsonProperty("item_name")
  private String itemName;
  
  @JsonProperty("unit_weight")
  private UnitWeight unitWeight;
  
  @JsonIgnore
  public String getItemName() {
    return itemName;
  }
  
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  
  @JsonIgnore
  public UnitWeight getUnitWeight() {
    return unitWeight;
  }
  
  public void setUnitWeight(UnitWeight unitWeight) {
    this.unitWeight = unitWeight;
  }
  
  @Override
  public String toString() {
    return "GroceryItem{" + "itemName='" + itemName + '\'' + ", unitWeight=" + unitWeight + '}';
  }
}
