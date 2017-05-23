package in.adavi.pradyot.web.assistant.service.application.filter.request;

import in.adavi.pradyot.web.assistant.service.application.Constant;
import in.adavi.pradyot.web.assistant.service.application.filter.FilterPriority;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@Priority(FilterPriority.AGENT_FILTER)
public class AgentFilter implements ContainerRequestFilter {
  
  private static final List<String> whitelistedAgentIds = new ArrayList<>();
  
  static {
    whitelistedAgentIds.add("Flipkart");
  }
  
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    
    if (containerRequestContext.getUriInfo().getPath().contains("swagger")) {
      return;
    }
    String agentId = containerRequestContext.getHeaderString(Constant.X_AGENT_ID);
    if (null == agentId || agentId.isEmpty() || !whitelistedAgentIds.contains(agentId)) {
      Exception cause = new IllegalAccessException("No merchant identifier found");
      throw new WebApplicationException(cause, Response.Status.FORBIDDEN);
    }
  }
}
