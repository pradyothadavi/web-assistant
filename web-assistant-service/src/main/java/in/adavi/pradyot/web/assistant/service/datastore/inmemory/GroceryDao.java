package in.adavi.pradyot.web.assistant.service.datastore.inmemory;

import in.adavi.pradyot.web.assistant.service.datastore.IGroceryFulfillmentDao;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;
import in.adavi.pradyot.web.assistant.service.datastore.entity.Khaata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class GroceryDao implements IGroceryFulfillmentDao {
  
  private static final Logger logger = LoggerFactory.getLogger(GroceryDao.class);
  
  private Map<String,GroceryList> groceryListMap;
  private Map<String,Khaata> khaataMap;
  
  public GroceryDao() {
    groceryListMap = new HashMap<>();
    GroceryList groceryList = new GroceryList();
    groceryListMap.put("default",groceryList);
    
    Khaata khaata = new Khaata();
    khaata.setCreditAvailed(0);
    khaata.setCreditLimit(5000);
    
    khaataMap = new HashMap<>();
    khaataMap.put("Pradyot",khaata);
    khaataMap.put("Suyash",khaata);
    khaataMap.put("Mayank",khaata);
    khaataMap.put("Amar",khaata);
    
  }
  
  @Override
  public void createList(String name) {
    this.groceryListMap.put(name,new GroceryList());
  }
  
  @Override
  public void addItem(GroceryItem groceryItem, GroceryList groceryList) {
    if(null == groceryItem)
      this.groceryListMap.get("default").addGroceryItem(groceryItem);
    groceryList.addGroceryItem(groceryItem);
  }
  
  @Override
  public GroceryList getList(String name) {
    return this.groceryListMap.get(name);
  }
  
  @Override
  public void checkoutGroceryList(String name) {
    this.groceryListMap.put(name,new GroceryList());
  }
  
  @Override
  public Khaata getGroceryKhaata(String userId) {
    return null;
  }
  
  @Override
  public void debitKhaata(String userId) {
    Random random = new Random();
    int low = 500;
    int high = 2500;
    int result = random.nextInt(high-low)+low;
    
    Khaata khaata = khaataMap.get(userId);
    int amt = khaata.getCreditAvailed() + result;
    khaata.setCreditAvailed(amt);
    
    khaataMap.put(userId,khaata);
  }
}
