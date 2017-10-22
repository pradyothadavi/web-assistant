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
  private final String asanaAccessToken;
//  private final KiranaStoreConfiguration kiranaStoreConfiguration;
//  private final DataSourceFactory database;

  @JsonCreator
  public WebAssistantConfiguration(@JsonProperty("swagger") SwaggerBundleConfiguration swaggerBundleConfiguration,
                                   @JsonProperty("apiAiAgentInteractor") ApiAiAgentInteractorConfiguration apiAiAgentInteractorConfiguration,
                                   @JsonProperty("asanaAccessToken") String asanaAccessToken) {
    
    this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    this.apiAiAgentInteractorConfiguration = apiAiAgentInteractorConfiguration;
//    this.kiranaStoreConfiguration = kiranaStoreConfiguration;
//    this.database = database;
    this.asanaAccessToken = asanaAccessToken;
  }
  
  public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
    return swaggerBundleConfiguration;
  }
  
  public ApiAiAgentInteractorConfiguration getApiAiAgentInteractorConfiguration() {
    return apiAiAgentInteractorConfiguration;
  }

  public String getAsanaAccessToken() {
    return asanaAccessToken;
  }

  //  public KiranaStoreConfiguration getKiranaStoreConfiguration() {
//    return kiranaStoreConfiguration;
//  }
//
//  public DataSourceFactory getDatabase() {
//    return database;
//  }
}
