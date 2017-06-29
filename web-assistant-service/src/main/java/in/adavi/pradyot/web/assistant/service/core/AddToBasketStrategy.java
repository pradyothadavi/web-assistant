package in.adavi.pradyot.web.assistant.service.core;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.FulfillmentStrategy;

/**
 * Created by pradyot.ha on 23/05/17.
 */
public abstract class AddToBasketStrategy implements FulfillmentStrategy {
  
  private QueryResponse queryResponse;
  private boolean isVerifiedUser;
  private boolean isAddToCart;
  
  @Override
  public FulfillmentServiceResponse fulfillWish(QueryResponse queryResponse) {
    this.queryResponse = queryResponse;
    this.isVerifiedUser = verifyUser();
    if(isVerifiedUser) {
      this.isAddToCart = addToCart(this.queryResponse);
    }
    FulfillmentServiceResponse fulfillmentServiceResponse = ask(this.queryResponse,this.isAddToCart);
    return fulfillmentServiceResponse;
  }
  
  protected abstract boolean addToCart(QueryResponse queryResponse);
  
  protected abstract boolean verifyUser();
  
  public abstract FulfillmentServiceResponse ask(QueryResponse queryResponse, boolean isAddToCart);
}
