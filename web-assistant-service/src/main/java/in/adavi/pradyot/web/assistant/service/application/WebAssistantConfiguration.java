package in.adavi.pradyot.web.assistant.service.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pradyothadavi.ApiAiAgentInteractorConfiguration;
import com.github.pradyothadavi.KiranaStoreConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * @author pradyot.ha
 */
public class WebAssistantConfiguration extends Configuration {
  private final SwaggerBundleConfiguration swaggerBundleConfiguration;
  private final ApiAiAgentInteractorConfiguration apiAiAgentInteractorConfiguration;
  private final KiranaStoreConfiguration kiranaStoreConfiguration;
  private final DataSourceFactory database;
  
  @JsonCreator
  public WebAssistantConfiguration(@JsonProperty("swagger") SwaggerBundleConfiguration swaggerBundleConfiguration, @JsonProperty("apiAiAgentInteractor") ApiAiAgentInteractorConfiguration apiAiAgentInteractorConfiguration, @JsonProperty("kiranaStoreConfiguration") KiranaStoreConfiguration kiranaStoreConfiguration,@JsonProperty("database") DataSourceFactory database) {
    
    this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    this.apiAiAgentInteractorConfiguration = apiAiAgentInteractorConfiguration;
    this.kiranaStoreConfiguration = kiranaStoreConfiguration;
    this.database = database;
  }
  
  public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
    return swaggerBundleConfiguration;
  }
  
  public ApiAiAgentInteractorConfiguration getApiAiAgentInteractorConfiguration() {
    return apiAiAgentInteractorConfiguration;
  }
  
  public KiranaStoreConfiguration getKiranaStoreConfiguration() {
    return kiranaStoreConfiguration;
  }
  
  public DataSourceFactory getDatabase() {
    return database;
  }
}
