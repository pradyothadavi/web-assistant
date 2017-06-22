package in.adavi.pradyot.web.assistant.service.application.fulfillment;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;

/**
 * Created by pradyot.ha on 23/05/17.
 */
public interface FulfillmentStrategy {
  
  FulfillmentServiceResponse fulfillWish(QueryResponse queryResponse);
}
