package in.adavi.pradyot.web.assistant.service.datastore.inmemory;

import in.adavi.pradyot.web.assistant.service.datastore.IGroceryFulfillmentDao;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class GroceryDao implements IGroceryFulfillmentDao {
  
  private static final Logger logger = LoggerFactory.getLogger(GroceryDao.class);
  
  private GroceryList groceryList;
  
  public GroceryDao() {
    groceryList = new GroceryList();
  }
  
  @Override
  public void createList(String name) {
  
  }
  
  @Override
  public void addItem(GroceryItem groceryItem) {
    groceryList.addGroceryItem(groceryItem);
  }
  
  @Override
  public GroceryList getList(String name) {
    return groceryList;
  }
}
