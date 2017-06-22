package in.adavi.pradyot.web.assistant.service.application;

import com.github.pradyothadavi.ApiAiAgentInteractorBundle;
import com.github.pradyothadavi.ApiAiAgentInteractorConfiguration;
import com.github.pradyothadavi.ApiAiAgentInteractorModule;
import com.github.pradyothadavi.KiranaStoreBundle;
import com.github.pradyothadavi.KiranaStoreConfiguration;
import com.github.pradyothadavi.KiranaStoreModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import in.adavi.pradyot.web.assistant.service.application.filter.FilterRegistration;
import in.adavi.pradyot.web.assistant.service.web.ApiAiResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pradyot.ha
 */
public class WebAssistantApplication extends Application<WebAssistantConfiguration> {
  
  private static final Logger logger = LoggerFactory.getLogger(WebAssistantApplication.class);
  
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
    
    bootstrap.addBundle(new KiranaStoreBundle<WebAssistantConfiguration>() {
      @Override
      protected KiranaStoreConfiguration getKiranaStoreConfiguration(WebAssistantConfiguration webAssistantConfiguration) {
        return webAssistantConfiguration.getKiranaStoreConfiguration();
      }
    });
  }
  
  @Override
  public void run(WebAssistantConfiguration webAssistantConfiguration, Environment environment) throws Exception {
    
    Injector injector = Guice.createInjector(new WebAssistantModule(webAssistantConfiguration),
                                             new ApiAiAgentInteractorModule(webAssistantConfiguration.getApiAiAgentInteractorConfiguration()),
                                             new KiranaStoreModule(webAssistantConfiguration.getKiranaStoreConfiguration()));
  
    environment.jersey().register(injector.getInstance(ApiAiResource.class));
    environment.jersey().register(FilterRegistration.class);
  }
}
