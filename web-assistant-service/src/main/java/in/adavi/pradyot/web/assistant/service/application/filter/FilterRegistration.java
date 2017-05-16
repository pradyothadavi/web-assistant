package in.adavi.pradyot.web.assistant.service.application.filter;

import in.adavi.pradyot.web.assistant.service.application.filter.request.AuthorizationFilter;
import in.adavi.pradyot.web.assistant.service.application.filter.request.ClientFilter;
import in.adavi.pradyot.web.assistant.service.application.filter.request.ContextFilter;
import in.adavi.pradyot.web.assistant.service.application.filter.request.MerchantFilter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class FilterRegistration implements DynamicFeature {
  @Override
  public void configure(ResourceInfo resourceInfo, FeatureContext featureContext) {
    
    if (useMerchantFilter(resourceInfo)) {
      featureContext.register(MerchantFilter.class);
    }
    if (useAuthorizationFilter(resourceInfo)) {
      featureContext.register(AuthorizationFilter.class);
    }
    if (useClientFilter(resourceInfo)) {
      featureContext.register(ClientFilter.class);
    }
    if(useContextFilter(resourceInfo)) {
      featureContext.register(ContextFilter.class);
    }
  }
  
  private boolean useContextFilter(ResourceInfo resourceInfo) {
    return true;
  }
  
  private boolean useClientFilter(ResourceInfo resourceInfo) {
    return true;
  }
  
  private boolean useAuthorizationFilter(ResourceInfo resourceInfo) {
    return true;
  }
  
  private boolean useMerchantFilter(ResourceInfo resourceInfo) {
    return true;
  }
}
