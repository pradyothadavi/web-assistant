package in.adavi.pradyot.web.assistant.service.application.fulfillment;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pradyot.ha on 23/05/17.
 */
public abstract class Webhook {
  
  private static final Logger logger = LoggerFactory.getLogger(Webhook.class);
  
  public FulfillmentServiceResponse fulfillWish(QueryResponse queryResponse){
    FulfillmentStrategy fulfillmentStrategy = identifyStrategy(queryResponse);
    FulfillmentServiceResponse fulfillmentServiceResponse = executeStrategy(fulfillmentStrategy,queryResponse);
    return fulfillmentServiceResponse;
  }
  
  public abstract FulfillmentStrategy identifyStrategy(QueryResponse queryResponse);
  
  public abstract FulfillmentServiceResponse executeStrategy(FulfillmentStrategy fulfillmentStrategy, QueryResponse queryResponse);
}
