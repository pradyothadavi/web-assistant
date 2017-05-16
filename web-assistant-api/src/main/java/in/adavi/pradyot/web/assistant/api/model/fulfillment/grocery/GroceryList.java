package in.adavi.pradyot.web.assistant.api.model.fulfillment.grocery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroceryList {
  
  @JsonProperty("name")
  private String name;
  
  @JsonProperty("groceryItems")
  private List<GroceryItem> groceryItems;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<GroceryItem> getGroceryItems() {
    return groceryItems;
  }
  
  public void setGroceryItems(List<GroceryItem> groceryItems) {
    this.groceryItems = groceryItems;
  }
  
  @Override
  public String toString() {
    return "GroceryList{" + "name='" + name + '\'' + ", groceryItems=" + groceryItems + '}';
  }
}
