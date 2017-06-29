package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.api.ai.model.Result;
import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.service.application.context.ApiAiFulfillmentContext;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.FulfillmentStrategy;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.Webhook;
import in.adavi.pradyot.web.assistant.service.core.AddToBasketStrategy;
import in.adavi.pradyot.web.assistant.service.core.CheckoutStrategy;
import in.adavi.pradyot.web.assistant.service.core.WelcomeStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pradyot.ha on 23/05/17.
 */
public class ApiAiWebhook extends Webhook {
  
  private static final Logger logger = LoggerFactory.getLogger(ApiAiWebhook.class);
  
  private WelcomeStrategy welcomeStrategy;
  private AddToBasketStrategy addToBasketStrategy;
  private CheckoutStrategy checkoutStrategy;
  
  @Inject
  public ApiAiWebhook(WelcomeStrategy welcomeStrategy, AddToBasketStrategy addToBasketStrategy, CheckoutStrategy checkoutStrategy) {
    this.welcomeStrategy = welcomeStrategy;
    this.addToBasketStrategy = addToBasketStrategy;
    this.checkoutStrategy = checkoutStrategy;
  }
  
  @Override
  public FulfillmentStrategy identifyStrategy(QueryResponse queryResponse) {
    
    FulfillmentStrategy fulfillmentStrategy = null;
    String agentId = ApiAiFulfillmentContext.getAgentId();
    switch (agentId){
      case "Flipkart":
        fulfillmentStrategy = getFlipkartFulfillmentStrategy(queryResponse);
        break;
    }
    return fulfillmentStrategy;
  }
  
  private FulfillmentStrategy getFlipkartFulfillmentStrategy(QueryResponse queryResponse) {
    FulfillmentStrategy fulfillmentStrategy = null;
    Result result = queryResponse.getResult();
    switch (result.getAction()){
      case "input.welcome":
        fulfillmentStrategy = welcomeStrategy;
        break;
      case "add-item":
        fulfillmentStrategy = addToBasketStrategy;
        break;
      case "checkout":
      case "checkout.checkout-yes":
      case "payment":
        fulfillmentStrategy = checkoutStrategy;
        break;
    }
    return fulfillmentStrategy;
  }
  
  @Override
  public FulfillmentServiceResponse executeStrategy(FulfillmentStrategy fulfillmentStrategy, QueryResponse queryResponse) {
    FulfillmentServiceResponse fulfillmentServiceResponse = fulfillmentStrategy.fulfillWish(queryResponse);
    return fulfillmentServiceResponse;
  }
}
