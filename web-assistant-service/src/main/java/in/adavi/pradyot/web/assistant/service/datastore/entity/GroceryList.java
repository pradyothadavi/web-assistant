package in.adavi.pradyot.web.assistant.service.datastore.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class GroceryList {
  
  private String name = "default";
  
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
  
  public void addGroceryItem(GroceryItem groceryItem){
    if(null == this.groceryItems){
      this.groceryItems = new ArrayList<>();
    }
    this.groceryItems.add(groceryItem);
  }
  
  @Override
  public String toString() {
    return "GroceryList{" + "name='" + name + '\'' + ", groceryItems=" + groceryItems + '}';
  }
}
