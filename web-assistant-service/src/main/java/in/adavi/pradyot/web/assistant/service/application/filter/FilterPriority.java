package in.adavi.pradyot.web.assistant.service.application.filter;

/**
 * @see javax.ws.rs.Priorities
 * Created by pradyot.ha on 16/05/17.
 */
public final class FilterPriority {
  public static final int MERCHANT_FILTER = 10;
  public static final int CLIENT_FILTER = 20;
  public static final int AUTHORIZATION_FILTER = 30;
  public static final int CONTEXT_FILTER = 100;
  
  private FilterPriority() {
  }
}
