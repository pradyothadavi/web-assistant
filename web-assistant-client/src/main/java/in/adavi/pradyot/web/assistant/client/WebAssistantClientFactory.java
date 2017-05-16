package in.adavi.pradyot.web.assistant.client;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class WebAssistantClientFactory {
  
  public static WebAssistantClient getStandardClient(String endpoint,String clientId){
    return new StdWebAssistantClient(endpoint,clientId);
  }
}
