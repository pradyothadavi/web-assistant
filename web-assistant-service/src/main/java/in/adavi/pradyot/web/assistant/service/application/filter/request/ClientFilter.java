package in.adavi.pradyot.web.assistant.service.application.filter.request;

import in.adavi.pradyot.web.assistant.service.application.Constant;
import in.adavi.pradyot.web.assistant.service.application.filter.FilterPriority;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@Priority(FilterPriority.CLIENT_FILTER)
public class ClientFilter implements ContainerRequestFilter {
  
  private static final Map<String, List<String>> agentClientMappingWhitelist = new HashMap<>();
  
  static {
    List<String> commonClients = new ArrayList<>();
    commonClients.add("cli");
    
    List<String> flipkartClients = new ArrayList<>();
    flipkartClients.add("google-assistant");
    flipkartClients.addAll(commonClients);
    
    agentClientMappingWhitelist.put("Flipkart", flipkartClients);
  }
  
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
  
    if (containerRequestContext.getUriInfo().getPath().contains("swagger")) {
      return;
    }
    String clientId = containerRequestContext.getHeaderString(Constant.X_CLIENT_ID);
    String agentId = containerRequestContext.getHeaderString(Constant.X_AGENT_ID);
    // Merchant filter has already checked for merchant identifier
    List<String> whitelistedClients = agentClientMappingWhitelist.get(agentId);
    
    if (StringUtils.isEmpty(clientId) || whitelistedClients.isEmpty() || !whitelistedClients.contains(clientId)) {
      Exception cause = new IllegalAccessException("No client identifier found for " + agentId);
      throw new WebApplicationException(cause, Response.Status.FORBIDDEN);
    }
  }
}
