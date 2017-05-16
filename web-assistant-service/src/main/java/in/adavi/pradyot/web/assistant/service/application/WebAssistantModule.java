package in.adavi.pradyot.web.assistant.service.application;

import com.google.inject.AbstractModule;
import in.adavi.pradyot.web.assistant.service.core.FulfillmentService;
import in.adavi.pradyot.web.assistant.service.core.v1.GroceryFulfillment;
import in.adavi.pradyot.web.assistant.service.datastore.IGroceryFulfillmentDao;
import in.adavi.pradyot.web.assistant.service.datastore.ISpeechResponseDao;
import in.adavi.pradyot.web.assistant.service.datastore.inmemory.GroceryDao;
import in.adavi.pradyot.web.assistant.service.datastore.inmemory.SpeechResponseDao;

/**
 * @author pradyot.ha
 */
public class WebAssistantModule extends AbstractModule {
  private WebAssistantConfiguration webAssistantConfiguration;
  
  public WebAssistantModule(WebAssistantConfiguration webAssistantConfiguration) {
    this.webAssistantConfiguration = webAssistantConfiguration;
  }
  
  @Override
  protected void configure() {
    
    bind(FulfillmentService.class).to(GroceryFulfillment.class);
    
    bind(IGroceryFulfillmentDao.class).to(GroceryDao.class);
    bind(ISpeechResponseDao.class).to(SpeechResponseDao.class);
  }
}
