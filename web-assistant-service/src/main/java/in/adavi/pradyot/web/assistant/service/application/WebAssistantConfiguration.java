package in.adavi.pradyot.web.assistant.service.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pradyothadavi.ApiAiAgentInteractorConfiguration;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * @author pradyot.ha
 */
public class WebAssistantConfiguration extends Configuration {
  private final SwaggerBundleConfiguration swaggerBundleConfiguration;
  private final ApiAiAgentInteractorConfiguration apiAiAgentInteractorConfiguration;
  
  @JsonCreator
  public WebAssistantConfiguration(@JsonProperty("swagger") SwaggerBundleConfiguration swaggerBundleConfiguration,
                                   @JsonProperty("apiAiAgentInteractor") ApiAiAgentInteractorConfiguration apiAiAgentInteractorConfiguration) {
    
    this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    this.apiAiAgentInteractorConfiguration = apiAiAgentInteractorConfiguration;
  }
  
  public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
    return swaggerBundleConfiguration;
  }
  
  public ApiAiAgentInteractorConfiguration getApiAiAgentInteractorConfiguration() {
    return apiAiAgentInteractorConfiguration;
  }
}
