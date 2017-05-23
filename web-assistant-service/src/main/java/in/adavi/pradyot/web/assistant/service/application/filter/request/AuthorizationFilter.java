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
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@Priority(FilterPriority.AUTHORIZATION_FILTER)
public class AuthorizationFilter implements ContainerRequestFilter {
  
  private static Map<String, String> agentIdToKeyMapping = new HashMap<>();
  
  static {
    agentIdToKeyMapping.put("Flipkart", "Flipkart");
  }
  
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    
    if (containerRequestContext.getUriInfo().getPath().contains("swagger")) {
      return;
    }
    String authorizationHeaderValue = containerRequestContext.getHeaderString(Constant.X_AUTHORIZATION);
    if (null == authorizationHeaderValue || StringUtils.isEmpty(authorizationHeaderValue)) {
      Exception cause = new IllegalAccessException("No authorization information found.");
      throw new WebApplicationException(cause, Response.Status.FORBIDDEN);
    }
    String agentId = containerRequestContext.getHeaderString(Constant.X_AGENT_ID);
    StringBuffer keyReverse = new StringBuffer(agentIdToKeyMapping.get(agentId));
    String input = agentId + ":" + keyReverse.reverse().toString();
    String base64encoded = Base64.getEncoder().encodeToString(input.getBytes());
    if (!base64encoded.equals(authorizationHeaderValue)) {
      Exception cause = new IllegalAccessException("No authorization information found.");
      throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
    }
  }
}
