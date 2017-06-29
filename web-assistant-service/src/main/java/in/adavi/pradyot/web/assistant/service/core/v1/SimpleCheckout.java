package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.Basket;
import com.github.pradyothadavi.Product;
import com.github.pradyothadavi.api.ai.model.Result;
import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.core.BasketService;
import com.github.pradyothadavi.google.action.model.ExpectedInput;
import com.github.pradyothadavi.google.action.model.ExpectedIntent;
import com.github.pradyothadavi.google.action.model.InputPrompt;
import com.github.pradyothadavi.google.action.model.Item;
import com.github.pradyothadavi.google.action.model.RichResponse;
import com.github.pradyothadavi.google.action.model.SimpleResponse;
import com.github.pradyothadavi.google.action.response.AppResponse;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.CheckoutStrategy;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pradyot.ha on 22/06/17.
 */
public class SimpleCheckout extends CheckoutStrategy {
  
  private static final Logger logger = LoggerFactory.getLogger(SimpleCheckout.class);
  
  private static final String RESPONSE_TEMPLATE = "The order is completed using Flipkart Pay Later. Visit Khata Cockpit to see more details. Have a nice day.";
  private static final String PAYMENT_RESPONSE_TEMPLATE = "Do you wish to make the payment right way or shall i note it done in Khaata ?";
  private static final String ORDER_RESPONSE_TEMPLATE = "Your order will be delivered to XXX location by Date. Have a nice day.";
  
  private Client client;
  private WebTarget webTarget;
  
  @Inject
  private BasketService basketService;
  
  public BasketService getBasketService() {
    return basketService;
  }
  
  public void setBasketService(BasketService basketService) {
    this.basketService = basketService;
  }
  
  @Override
  protected FulfillmentServiceResponse ask(QueryResponse queryResponse) {
  
    client = ClientBuilder.newClient();
    client.property(ClientProperties.CONNECT_TIMEOUT, 10000);
    client.property(ClientProperties.READ_TIMEOUT, 10000);
  
    this.webTarget = client.target("http://172.20.14.181:8180");
    
    Result result = queryResponse.getResult();
    String action = result.getAction();
  
    boolean expectedUserResponse = true;
    Basket basket = basketService.get(queryResponse.getSessionId());
    logger.info("Basket : {}",basket);
    String responseMsg = null;
    switch (action)
    {
      case "checkout":
        String amtInString = getBasketAmount(basket);
        responseMsg = String.format(RESPONSE_TEMPLATE,amtInString);
        expectedUserResponse = false;
        break;
      case "checkout.checkout-yes":
        responseMsg = String.format(PAYMENT_RESPONSE_TEMPLATE);
        break;
      case "payment":
        responseMsg = String.format(ORDER_RESPONSE_TEMPLATE);
        expectedUserResponse = false;
        break;
    }
    SimpleResponse simpleResponse = new SimpleResponse();
    simpleResponse.setTextToSpeech(responseMsg);
    simpleResponse.setDisplayText(responseMsg);
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
    appResponse.setExpectUserResponse(expectedUserResponse);
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
  
  private String getBasketAmount(Basket basket) {
    Response response = null;

//    try
//    {
//      response = webTarget.path("/bnpl-shylock/1/checkout/2.VI617075B70E1244F79E6A0362FA6B3D64.SI878282B40DC94DA69C67173A6668FBCF.VS149816076253711786150.1498194769").request(MediaType.APPLICATION_JSON)
//                   .post(Entity.entity(null, MediaType.APPLICATION_JSON));
//      if (Response.Status.OK.getStatusCode() == response.getStatus())
//      {
//
//      }
//    }
//    catch (Exception e)
//    {
//      if (null != response)
//      {
//        response.close();
//      }
//    }
    String amtInString = null;
    double amt= 0;
    for(Product product: basket.getProducts())
    {
      amt = amt + (product.getNoOfUnits() * product.getPricePerUnit());
    }
    amtInString = String.valueOf(amt);
    return amtInString;
  }
}
