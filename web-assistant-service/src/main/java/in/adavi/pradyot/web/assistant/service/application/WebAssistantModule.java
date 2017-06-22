package in.adavi.pradyot.web.assistant.service.application;

import com.github.pradyothadavi.core.AiConversationService;
import com.github.pradyothadavi.core.v1.TextConversation;
import com.google.inject.AbstractModule;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.Webhook;
import in.adavi.pradyot.web.assistant.service.core.AddToBasketStrategy;
import in.adavi.pradyot.web.assistant.service.core.FulfillmentService;
import in.adavi.pradyot.web.assistant.service.core.UserService;
import in.adavi.pradyot.web.assistant.service.core.WelcomeStrategy;
import in.adavi.pradyot.web.assistant.service.core.v1.AddToGroceryBasket;
import in.adavi.pradyot.web.assistant.service.core.v1.ApiAiWebhook;
import in.adavi.pradyot.web.assistant.service.core.v1.GroceryFulfillment;
import in.adavi.pradyot.web.assistant.service.core.v1.GroceryStoreWelcome;
import in.adavi.pradyot.web.assistant.service.core.v1.UserManager;
import in.adavi.pradyot.web.assistant.service.datastore.IGroceryFulfillmentDao;
import in.adavi.pradyot.web.assistant.service.datastore.ISpeechResponseDao;
import in.adavi.pradyot.web.assistant.service.datastore.IUserDao;
import in.adavi.pradyot.web.assistant.service.datastore.inmemory.GroceryDao;
import in.adavi.pradyot.web.assistant.service.datastore.inmemory.SpeechResponseDao;
import in.adavi.pradyot.web.assistant.service.datastore.inmemory.UserDao;

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
    
    bind(Webhook.class).to(ApiAiWebhook.class);
    bind(WelcomeStrategy.class).to(GroceryStoreWelcome.class);
    bind(AddToBasketStrategy.class).to(AddToGroceryBasket.class);
    
    bind(AiConversationService.class).to(TextConversation.class);
    bind(FulfillmentService.class).to(GroceryFulfillment.class);
    bind(UserService.class).to(UserManager.class);
    
    bind(IGroceryFulfillmentDao.class).to(GroceryDao.class);
    bind(ISpeechResponseDao.class).to(SpeechResponseDao.class);
    bind(IUserDao.class).to(UserDao.class);
  }
}
