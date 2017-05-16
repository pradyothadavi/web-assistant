package in.adavi.pradyot.web.assistant.client;

import in.adavi.pradyot.web.assistant.api.model.Chat;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Base64;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class StdWebAssistantClient implements WebAssistantClient {
  
  private final Client client;
  private final WebTarget webTarget;
  private final String clientId;
  
  public StdWebAssistantClient(String endpoint,String clientId) {
    if(StringUtils.isEmpty(endpoint))
      throw new IllegalArgumentException("Endpoint cannot be blank");
  
    client = ClientBuilder.newClient();
    client.property(ClientProperties.CONNECT_TIMEOUT, 10000);
    client.property(ClientProperties.READ_TIMEOUT, 10000);
  
    this.webTarget = client.target(endpoint);
    this.clientId = clientId;
  }
  
  private Response makePostApiCall(String path, MultivaluedMap<String, Object> headers, Object request)
    throws WebAssistantClientException
  {
    Response response = null;
    
    try
    {
      response = webTarget.path(path).request(MediaType.APPLICATION_JSON).headers(headers)
                   .post(Entity.entity(request, MediaType.APPLICATION_JSON));
      if (Response.Status.OK.getStatusCode() == response.getStatus())
      {
        return response;
      }
    }
    catch (Exception e)
    {
      if (null != response)
      {
        response.close();
      }
      // Unfortunately we have to catch all Exceptions as Jersey does not throw checked exceptions
      throw new WebAssistantClientException(e);
    }
    return response;
  }
  
  private Response makeGetApiCall(String path, MultivaluedMap<String, Object> headers) throws WebAssistantClientException
  {
    Response response = null;
    
    try
    {
      response = webTarget.path(path).request(MediaType.APPLICATION_JSON).headers(headers).get();
      if (Response.Status.OK.getStatusCode() == response.getStatus())
      {
        return response;
      }
    }
    catch (Exception e)
    {
      if (null != response)
      {
        response.close();
      }
      // Unfortunately we have to catch all Exceptions as Jersey does not throw checked exceptions
      throw new WebAssistantClientException(e);
    }
    return response;
  }
  
  private String getAuthorizationHeaderValue(String merchantId){
    StringBuffer keyReverse = new StringBuffer(merchantId);
    String input = merchantId + ":" + keyReverse.reverse().toString();
    String base64encoded = Base64.getEncoder().encodeToString(input.getBytes());
    return base64encoded;
  }
  public Chat shop(Chat chat, String merchantId) throws WebAssistantClientException {
    
    Chat recepientResponse = null;
    
    String path = String.format(Constant.APP_CONTEXT_PATH+Constant.KIRANA_STORE_SHOP,"v1");
  
    MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();
    headers.add(Constant.X_MERCHANT_ID, merchantId);
    headers.add(Constant.X_AUTHORIZATION,getAuthorizationHeaderValue(merchantId));
    headers.add(Constant.X_CLIENT_ID,clientId);
  
    Response response = makePostApiCall(path,headers,chat);
    recepientResponse = response.readEntity(Chat.class);
    return recepientResponse;
  }
}
