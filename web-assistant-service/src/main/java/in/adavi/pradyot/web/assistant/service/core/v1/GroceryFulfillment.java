package in.adavi.pradyot.web.assistant.service.core.v1;


import com.github.pradyothadavi.api.ai.model.Context;
import com.github.pradyothadavi.api.ai.model.Result;
import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.google.action.model.*;
import com.github.pradyothadavi.google.action.response.AppResponse;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.FulfillmentService;
import in.adavi.pradyot.web.assistant.service.datastore.IGroceryFulfillmentDao;
import in.adavi.pradyot.web.assistant.service.datastore.ISpeechResponseDao;
import in.adavi.pradyot.web.assistant.service.datastore.entity.Khaata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    List<Context> contexts = result.getContexts();
    Context userContext = getUserContext(contexts);
    String speechTemplate;
    String speech;
    
    switch (action){
      
      case "checkout":
        iGroceryFulfillmentDao.checkoutGroceryList(userContext.getParameters().get("firstName").toString());
        iGroceryFulfillmentDao.debitKhaata(userContext.getParameters().get("firstName").toString());
        Khaata khaata = iGroceryFulfillmentDao.getGroceryKhaata(userContext.getParameters().get("firstName").toString());
        
        speech = "Your order is confirmed. So far you have availed INR "+khaata.getCreditAvailed()+" credit out of INR "+khaata.getCreditLimit();
        fulfillmentServiceResponse = new FulfillmentServiceResponse();
        fulfillmentServiceResponse.setSpeech(speech);
        fulfillmentServiceResponse.setDisplayText(speech);
        fulfillmentServiceResponse.setSource("grocery-fulfillment");
        fulfillmentServiceResponse.setData(null);
        break;
        
      case "add-item":
        fulfillmentServiceResponse = getFulfillmentServiceResponse(action);
        break;
        
      case "input.welcome":
        fulfillmentServiceResponse = getFulfillmentServiceResponse(action);
        break;
        
      default:
        fulfillmentServiceResponse = new FulfillmentServiceResponse();
        fulfillmentServiceResponse.setSpeech("Sorry I could not understand that. Can you elaborate ?");
        fulfillmentServiceResponse.setDisplayText("Sorry I could not understand that. Can you elaborate ?");
        fulfillmentServiceResponse.setSource("grocery-fulfillment");
        fulfillmentServiceResponse.setData(null);
        break;
    }
    return fulfillmentServiceResponse;
  }
  
  private FulfillmentServiceResponse getFulfillmentServiceResponse(String action) {
    SimpleResponse simpleResponse = new SimpleResponse();
  
    BasicCard basicCard = new BasicCard();
  
    if(action.equals("input.welcome")){
      simpleResponse.setDisplayText("Hey user from fulfillment, welcome to the grocery store. What items do you wish to order ?");
      simpleResponse.setTextToSpeech("Hey user from fulfillment, welcome to the grocery store. What items do you wish to order ?");
    } else {
      simpleResponse.setTextToSpeech("Test Card");
      Image image = new Image();
      image.setUrl("http://placehold.it/350x150");
      image.setAccessibilityText("Image alternate text");
      image.setHeight(20);
      image.setWidth(20);
    
      basicCard.setImage(image);
      basicCard.setTitle("Test Card");
      basicCard.setFormattedText("Test Card");
    }
  
    Item item1 = new Item();
    item1.setSimpleResponse(simpleResponse);
    
    Item item2 = new Item();
    item2.setBasicCard(basicCard);
  
    List<Item> items = new ArrayList<>();
    items.add(item1);
    items.add(item2);
  
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
  
  private Context getUserContext(List<Context> contexts) {
    for (Context context:
         contexts) {
      if("user-context".equals(context.getName())){
        return context;
      }
    }
    return null;
  }
}
