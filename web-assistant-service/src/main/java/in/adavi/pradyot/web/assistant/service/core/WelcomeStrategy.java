package in.adavi.pradyot.web.assistant.service.core;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.FulfillmentStrategy;

/**
 * Created by pradyot.ha on 23/05/17.
 */
public abstract class WelcomeStrategy implements FulfillmentStrategy{
  
  private QueryResponse queryResponse;
  
  @Override
  public FulfillmentServiceResponse fulfillWish(QueryResponse queryResponse) {
    this.queryResponse = queryResponse;
    FulfillmentServiceResponse fulfillmentServiceResponse = greetUser(this.queryResponse);
    return fulfillmentServiceResponse;
  }
  
  public abstract FulfillmentServiceResponse greetUser(QueryResponse queryResponse);
}
