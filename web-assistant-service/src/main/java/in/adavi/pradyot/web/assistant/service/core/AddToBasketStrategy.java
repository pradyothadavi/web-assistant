package in.adavi.pradyot.web.assistant.service.core;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.FulfillmentStrategy;

/**
 * Created by pradyot.ha on 23/05/17.
 */
public abstract class AddToBasketStrategy implements FulfillmentStrategy {
  
  private QueryResponse queryResponse;
  
  @Override
  public FulfillmentServiceResponse fulfillWish(QueryResponse queryResponse) {
    this.queryResponse = queryResponse;
    boolean isVerifiedUser = verifyUser();
    if(isVerifiedUser) {
      boolean isAddToCart = addToCart();
    }
    FulfillmentServiceResponse fulfillmentServiceResponse = ask(this.queryResponse);
    return fulfillmentServiceResponse;
  }
  
  protected abstract boolean addToCart();
  
  protected abstract boolean verifyUser();
  
  public abstract FulfillmentServiceResponse ask(QueryResponse queryResponse);
}
