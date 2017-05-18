package in.adavi.pradyot.web.assistant.service.datastore;

import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;
import in.adavi.pradyot.web.assistant.service.datastore.entity.Khaata;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public interface IGroceryFulfillmentDao {
  
  void createList(String name);
  
  void addItem(GroceryItem groceryItem,GroceryList groceryList);
  
  GroceryList getList(String name);
  
  void checkoutGroceryList(String name);
  
  Khaata getGroceryKhaata(String userId);
  
  void debitKhaata(String userId);
}
