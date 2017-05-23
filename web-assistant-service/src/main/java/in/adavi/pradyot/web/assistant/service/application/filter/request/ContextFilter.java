package in.adavi.pradyot.web.assistant.service.application.filter.request;

import in.adavi.pradyot.web.assistant.service.application.Constant;
import in.adavi.pradyot.web.assistant.service.application.context.KiranaStoreContext;
import in.adavi.pradyot.web.assistant.service.application.filter.FilterPriority;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@Priority(FilterPriority.CONTEXT_FILTER)
public class ContextFilter implements ContainerRequestFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    
    KiranaStoreContext.reset();
    
    MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();
    
    String requestId = containerRequestContext.getHeaderString(Constant.X_REQUEST_ID);
    if (StringUtils.isEmpty(requestId)) {
      requestId = UUID.randomUUID().toString();
      headers.add(Constant.X_REQUEST_ID, requestId);
      
      MDC.put(Constant.X_REQUEST_ID, requestId);
    }
    
    String agentId = containerRequestContext.getHeaderString(Constant.X_AGENT_ID);
    
    KiranaStoreContext.setAgentId(agentId);
    KiranaStoreContext.setHeaders(headers);
  }
}
