package in.adavi.pradyot.web.assistant.service.application.context;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class ApiAiFulfillmentContext {
  
  private static final ThreadLocal<String> threadLocalAgentId = new ThreadLocal<>();
  private static final ThreadLocal<MultivaluedMap<String, String>> threadLocalHeaders = new ThreadLocal<>();
  
  public static void reset() {
    threadLocalAgentId.remove();
    threadLocalHeaders.remove();
  }
  
  public static String getAgentId() {
    return threadLocalAgentId.get();
  }
  
  public static void setAgentId(String agentId) {
    threadLocalAgentId.set(agentId);
  }
  
  public static MultivaluedMap<String, String> getHeaders() {
    return threadLocalHeaders.get();
  }
  
  public static void setHeaders(MultivaluedMap<String, String> headers) {
    threadLocalHeaders.set(headers);
  }
  
  public static List<String> getHeaderValues(String key) {
    MultivaluedMap<String, String> headers = threadLocalHeaders.get();
    List<String> values = headers.get(key);
    return values;
  }
}
