package in.adavi.pradyot.web.assistant.client;

import in.adavi.pradyot.web.assistant.api.model.Chat;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public interface WebAssistantClient {
  
  Chat shop(Chat chat,String merchantId) throws WebAssistantClientException;
}
