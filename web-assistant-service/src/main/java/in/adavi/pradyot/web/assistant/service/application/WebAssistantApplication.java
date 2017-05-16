package in.adavi.pradyot.web.assistant.service.application;

import com.github.pradyothadavi.ApiAiAgentInteractorBundle;
import com.github.pradyothadavi.ApiAiAgentInteractorConfiguration;
import com.github.pradyothadavi.ApiAiAgentInteractorModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import in.adavi.pradyot.web.assistant.service.application.filter.FilterRegistration;
import in.adavi.pradyot.web.assistant.service.web.FulfillmentResource;
import in.adavi.pradyot.web.assistant.service.web.KiranaStoreResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * @author pradyot.ha
 */
public class WebAssistantApplication extends Application<WebAssistantConfiguration> {
  
  @Override
  public void initialize(Bootstrap<WebAssistantConfiguration> bootstrap) {
    bootstrap.addBundle(new SwaggerBundle<WebAssistantConfiguration>() {
      @Override
      protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WebAssistantConfiguration webAssistantConfiguration) {
        return webAssistantConfiguration.getSwaggerBundleConfiguration();
      }
    });
    
    bootstrap.addBundle(new ApiAiAgentInteractorBundle<WebAssistantConfiguration>() {
      @Override
      protected ApiAiAgentInteractorConfiguration getApiAiAgentInteractorConfiguration(WebAssistantConfiguration webAssistantConfiguration) {
        return webAssistantConfiguration.getApiAiAgentInteractorConfiguration();
      }
    });
  }
  
  @Override
  public void run(WebAssistantConfiguration webAssistantConfiguration, Environment environment) throws Exception {
    Injector injector = Guice.createInjector(new WebAssistantModule(webAssistantConfiguration),
                                             new ApiAiAgentInteractorModule(webAssistantConfiguration.getApiAiAgentInteractorConfiguration()));
    
    environment.jersey().register(injector.getInstance(KiranaStoreResource.class));
    environment.jersey().register(injector.getInstance(FulfillmentResource.class));
    environment.jersey().register(FilterRegistration.class);
  }
}
