package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.model.FulfillmentServiceResponse;
import com.github.pradyothadavi.model.QueryResponse;
import com.github.pradyothadavi.model.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.service.core.FulfillmentService;
import in.adavi.pradyot.web.assistant.service.datastore.IGroceryFulfillmentDao;
import in.adavi.pradyot.web.assistant.service.datastore.ISpeechResponseDao;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;
import in.adavi.pradyot.web.assistant.service.datastore.entity.UnitWeight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class GroceryFulfillment implements FulfillmentService {
  
  private static final Logger logger = LoggerFactory.getLogger(GroceryFulfillment.class);
  
  private static List<String> nonAvailableGroceryItems;
  
  private IGroceryFulfillmentDao iGroceryFulfillmentDao;
  private ISpeechResponseDao iSpeechResponseDao;
  
  static {
    nonAvailableGroceryItems = new ArrayList<>();
    nonAvailableGroceryItems.add("Sugar");
    nonAvailableGroceryItems.add("Toor dal");
  }
  
  @Inject
  public GroceryFulfillment(IGroceryFulfillmentDao iGroceryFulfillmentDao, ISpeechResponseDao iSpeechResponseDao) {
    this.iGroceryFulfillmentDao = iGroceryFulfillmentDao;
    this.iSpeechResponseDao = iSpeechResponseDao;
  }
  
  @Override
  public FulfillmentServiceResponse processFulfillmentRequest(QueryResponse queryResponse) {
  
    FulfillmentServiceResponse fulfillmentServiceResponse = null;
    Result result = queryResponse.getResult();
    String action = result.getAction();
    switch (action){
      case "add-item":
        Map<String,Object> parameters = result.getParameters();
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setItemName(parameters.get("grocery-item").toString());
        groceryItem.setListingId(parameters.get("grocery-item").toString());
        
        String unitWeightInString = parameters.get("unit-weight").toString();
        UnitWeight unitWeight = null;
       
        Gson gson = new GsonBuilder().create();
        unitWeight = gson.fromJson(unitWeightInString,UnitWeight.class);
        groceryItem.setUnitWeight(unitWeight);
        
        iGroceryFulfillmentDao.addItem(groceryItem);
  
        GroceryList groceryList = iGroceryFulfillmentDao.getList("default");
        
        String speechTemplate = iSpeechResponseDao.getSpeechTemplate(result.getMetadata().getIntentId());
        String speech = String.format(speechTemplate,String.valueOf(groceryItem.getUnitWeight().getAmount()),groceryItem.getUnitWeight().getUnit(),groceryItem.getItemName());
        
        fulfillmentServiceResponse = new FulfillmentServiceResponse();
        fulfillmentServiceResponse.setSpeech(speech);
        fulfillmentServiceResponse.setDisplayText(speech);
        fulfillmentServiceResponse.setSource("grocery-fulfillment");
        fulfillmentServiceResponse.setData(groceryList);
    }
    return fulfillmentServiceResponse;
  }
}
