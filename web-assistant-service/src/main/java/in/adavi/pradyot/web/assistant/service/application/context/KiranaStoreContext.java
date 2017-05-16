package in.adavi.pradyot.web.assistant.service.application.context;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class KiranaStoreContext {
  
  private static final ThreadLocal<String> threadLocalMerchantId = new ThreadLocal<>();
  private static final ThreadLocal<MultivaluedMap<String, String>> threadLocalHeaders = new ThreadLocal<>();
  
  public static void reset() {
    threadLocalMerchantId.remove();
    threadLocalHeaders.remove();
  }
  
  public static String getMerchantId() {
    return threadLocalMerchantId.get();
  }
  
  public static void setMerchantId(String merchantId) {
    threadLocalMerchantId.set(merchantId);
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
