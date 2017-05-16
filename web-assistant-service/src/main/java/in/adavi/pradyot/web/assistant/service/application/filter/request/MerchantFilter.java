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
@Priority(FilterPriority.MERCHANT_FILTER)
public class MerchantFilter implements ContainerRequestFilter {
  
  private static final List<String> whitelistedMerchantIds = new ArrayList<>();
  
  static {
    whitelistedMerchantIds.add("kirana-store");
    whitelistedMerchantIds.add("Flipkart");
    whitelistedMerchantIds.add("API.AI");
  }
  
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    
    if (containerRequestContext.getUriInfo().getPath().contains("swagger")) {
      return;
    }
    String merchantId = containerRequestContext.getHeaderString(Constant.X_MERCHANT_ID);
    if (null == merchantId || merchantId.isEmpty() || !whitelistedMerchantIds.contains(merchantId)) {
      Exception cause = new IllegalAccessException("No merchant identifier found");
      throw new WebApplicationException(cause, Response.Status.FORBIDDEN);
    }
  }
}
