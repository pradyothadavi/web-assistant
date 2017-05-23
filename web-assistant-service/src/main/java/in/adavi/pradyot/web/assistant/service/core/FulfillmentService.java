package in.adavi.pradyot.web.assistant.service.core;


import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public interface FulfillmentService {
  
  FulfillmentServiceResponse processFulfillmentRequest(QueryResponse queryResponse);
}
