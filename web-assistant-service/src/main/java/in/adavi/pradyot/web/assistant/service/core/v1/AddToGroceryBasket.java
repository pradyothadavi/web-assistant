package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.google.action.model.*;
import com.github.pradyothadavi.google.action.response.AppResponse;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.AddToBasketStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pradyot.ha on 24/05/17.
 */
public class AddToGroceryBasket extends AddToBasketStrategy {
  
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
    
    SimpleResponse simpleResponse = new SimpleResponse();
    simpleResponse.setTextToSpeech("Added item to basket. Next.");
    simpleResponse.setDisplayText("Added item to basket. Next.");
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
