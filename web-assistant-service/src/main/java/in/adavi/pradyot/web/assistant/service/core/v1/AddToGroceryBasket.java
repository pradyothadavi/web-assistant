package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.api.ai.model.*;
import com.github.pradyothadavi.api.ai.model.Button;
import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.google.action.model.*;
import com.github.pradyothadavi.google.action.response.AppResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.AddToBasketStrategy;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;
import in.adavi.pradyot.web.assistant.service.datastore.entity.UnitWeight;

import java.util.*;

/**
 * Created by pradyot.ha on 24/05/17.
 */
public class AddToGroceryBasket extends AddToBasketStrategy {
  
  private Map<String,GroceryList> groceryListMap;
  
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
    
    SimpleResponse simpleResponse = new SimpleResponse();
    simpleResponse.setTextToSpeech("Added "+unitWeight.getAmount()+" "+unitWeight.getUnit()+" of "+groceryItem.getItemName()+" to basket. Next.");
    simpleResponse.setDisplayText("Added "+unitWeight.getAmount()+" "+unitWeight.getUnit()+" of "+groceryItem.getItemName()+" to basket. Next.");
    simpleResponse.setSsml(null);
    
    Item simpleItem = new Item();
    simpleItem.setSimpleResponse(simpleResponse);
    
    BasicCard basicCard = new BasicCard();
    basicCard.setTitle("Buy Now");
    basicCard.setSubtitle("Pay Later");
    
    Image image = new Image();
    image.setUrl("https://www.google.com/search?q=42");
    
    basicCard.setImage(image);
  
    com.github.pradyothadavi.google.action.model.Button btn = new com.github.pradyothadavi.google.action.model.Button();
    btn.setTitle("Buy Now");
    OpenUrlAction openUrlAction = new OpenUrlAction();
    openUrlAction.setUrl("https://www.google.com/search?q=42");
    btn.setOpenUrlAction(openUrlAction);
    
    basicCard.setButtons(Arrays.asList(btn));
    
    Item basicCardItem = new Item();
    basicCardItem.setBasicCard(basicCard);
    
    List<Item> items = new ArrayList<>();
    items.add(simpleItem);
    items.add(basicCardItem);
    
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
  
    CardMessage cardMessage = new CardMessage();
    cardMessage.setTitle("Buy Now");
    cardMessage.setSubtitle("Pay Later");
  
    Button button = new Button();
    button.setText("Buy Now");
    button.setPostback("https://www.google.com");
    
    cardMessage.setButtons(Arrays.asList(button));
    
    FulfillmentServiceResponse fulfillmentServiceResponse;
    fulfillmentServiceResponse = new FulfillmentServiceResponse();
    fulfillmentServiceResponse.setSpeech(simpleResponse.getTextToSpeech());
    fulfillmentServiceResponse.setDisplayText(simpleResponse.getDisplayText());
    fulfillmentServiceResponse.setSource("grocery-fulfillment");
    fulfillmentServiceResponse.setData(googleActionResponse);
    fulfillmentServiceResponse.setResponseMessages(Arrays.asList(cardMessage));
  
    return fulfillmentServiceResponse;
  }
}
