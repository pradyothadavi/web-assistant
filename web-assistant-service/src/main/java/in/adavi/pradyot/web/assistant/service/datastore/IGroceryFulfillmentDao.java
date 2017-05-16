package in.adavi.pradyot.web.assistant.service.datastore;

import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public interface IGroceryFulfillmentDao {
  
  void createList(String name);
  
  void addItem(GroceryItem groceryItem);
  
  GroceryList getList(String name);
}
