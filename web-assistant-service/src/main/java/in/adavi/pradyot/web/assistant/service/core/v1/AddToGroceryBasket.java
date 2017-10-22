package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.api.ai.model.Result;
import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.google.action.model.ExpectedInput;
import com.github.pradyothadavi.google.action.model.ExpectedIntent;
import com.github.pradyothadavi.google.action.model.InputPrompt;
import com.github.pradyothadavi.google.action.model.Item;
import com.github.pradyothadavi.google.action.model.RichResponse;
import com.github.pradyothadavi.google.action.model.SimpleResponse;
import com.github.pradyothadavi.google.action.response.AppResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.AddToBasketStrategy;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;
import in.adavi.pradyot.web.assistant.service.datastore.entity.UnitWeight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pradyot.ha on 24/05/17.
 */
public class AddToGroceryBasket extends AddToBasketStrategy {
  
  private static final Logger logger = LoggerFactory.getLogger(AddToGroceryBasket.class);
  
  private Map<String,GroceryList> groceryListMap;
  
//  @Inject
//  private ProductService productService;
//
//  public ProductService getProductService() {
//    return productService;
//  }
//
//  public void setProductService(ProductService productService) {
//    this.productService = productService;
//  }
  
  @Override
  protected boolean addToCart() {
    return false;
  }
  
  @Override
  protected boolean verifyUser() {
    return true;
  }
  
  @Override
  public FulfillmentServiceResponse ask(QueryResponse queryResponse) {
  
    if(null == groceryListMap)
      groceryListMap = new HashMap<>();
    
    GroceryList groceryList = new GroceryList();
    if(!groceryListMap.containsKey(queryResponse.getSessionId()))
      groceryListMap.put(queryResponse.getSessionId(),groceryList);
    else
      groceryList = groceryListMap.get(queryResponse.getSessionId());
    
    Result result = queryResponse.getResult();
    Map<String,Object> parameters = result.getParameters();
  
    
    Gson gson = new GsonBuilder().create();
    UnitWeight unitWeight = gson.fromJson(parameters.get("unit_weight").toString(),UnitWeight.class);
    
    GroceryItem groceryItem = new GroceryItem();
    groceryItem.setItemName(parameters.get("grocery_item").toString());
    groceryItem.setUnitWeight(unitWeight);
    
    groceryList.addGroceryItem(groceryItem);
  
//    Product product = productService.get(groceryItem.getItemName(),"Flipkart");
//
//    logger.info("Product : {}",product);
    
    SimpleResponse simpleResponse = new SimpleResponse();
    simpleResponse.setTextToSpeech("Added "+unitWeight.getAmount()+" "+unitWeight.getUnit()+" of "+groceryItem.getItemName()+" to basket. Next.");
    simpleResponse.setDisplayText("Added "+unitWeight.getAmount()+" "+unitWeight.getUnit()+" of "+groceryItem.getItemName()+" to basket. Next.");
    simpleResponse.setSsml(null);
    
    Item simpleItem = new Item();
    simpleItem.setSimpleResponse(simpleResponse);
    
    List<Item> items = new ArrayList<>();
    items.add(simpleItem);
    
    RichResponse richResponse = new RichResponse();
    richResponse.setItems(items);
  
    InputPrompt inputPrompt = new InputPrompt();
    inputPrompt.setRichInitialPrompt(richResponse);
  
    ExpectedIntent expectedIntent = new ExpectedIntent();
    expectedIntent.setIntent("actions.intent.TEXT");
  
    List<ExpectedIntent> expectedIntents = new ArrayList<>();
    expectedIntents.add(expectedIntent);
  
    ExpectedInput expectedInput = new ExpectedInput();
    expectedInput.setInputPrompt(inputPrompt);
    expectedInput.setPossibleIntents(expectedIntents);
  
    AppResponse appResponse = new AppResponse();
    appResponse.setExpectUserResponse(true);
    appResponse.setExpectedInputs(Arrays.asList(expectedInput));
  
    GoogleActionResponse googleActionResponse = new GoogleActionResponse();
    googleActionResponse.setData(appResponse);
    
    FulfillmentServiceResponse fulfillmentServiceResponse;
    fulfillmentServiceResponse = new FulfillmentServiceResponse();
    fulfillmentServiceResponse.setSpeech(simpleResponse.getTextToSpeech());
    fulfillmentServiceResponse.setDisplayText(simpleResponse.getDisplayText());
    fulfillmentServiceResponse.setSource("grocery-fulfillment");
    fulfillmentServiceResponse.setData(googleActionResponse);
    
    return fulfillmentServiceResponse;
  }
}
