package in.adavi.pradyot.web.assistant.service.core;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.FulfillmentStrategy;

/**
 * Created by pradyot.ha on 22/06/17.
 */
public abstract class CheckoutStrategy implements FulfillmentStrategy {
  
  private QueryResponse queryResponse;
  
  @Override
  public FulfillmentServiceResponse fulfillWish(QueryResponse queryResponse) {
    this.queryResponse = queryResponse;
  
    FulfillmentServiceResponse fulfillmentServiceResponse = ask(this.queryResponse);
    return fulfillmentServiceResponse;
  }
  
  protected abstract FulfillmentServiceResponse ask(QueryResponse queryResponse);
}
