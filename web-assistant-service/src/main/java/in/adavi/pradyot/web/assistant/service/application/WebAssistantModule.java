package in.adavi.pradyot.web.assistant.service.application;

import com.github.pradyothadavi.core.AiConversationService;
import com.github.pradyothadavi.core.v1.TextConversation;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import in.adavi.pradyot.web.assistant.service.application.fulfillment.Webhook;
import in.adavi.pradyot.web.assistant.service.core.AddToBasketStrategy;
import in.adavi.pradyot.web.assistant.service.core.CheckoutStrategy;
import in.adavi.pradyot.web.assistant.service.core.UserService;
import in.adavi.pradyot.web.assistant.service.core.WelcomeStrategy;
import in.adavi.pradyot.web.assistant.service.core.v1.AddToGroceryBasket;
import in.adavi.pradyot.web.assistant.service.core.v1.ApiAiWebhook;
import in.adavi.pradyot.web.assistant.service.core.v1.GroceryStoreWelcome;
import in.adavi.pradyot.web.assistant.service.core.v1.SimpleCheckout;
import in.adavi.pradyot.web.assistant.service.core.v1.UserManager;
import in.adavi.pradyot.web.assistant.service.datastore.IUserDao;
import in.adavi.pradyot.web.assistant.service.datastore.inmemory.UserDao;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

/**
 * @author pradyot.ha
 */
public class WebAssistantModule extends AbstractModule {
  private WebAssistantConfiguration webAssistantConfiguration;
  private HibernateBundle<WebAssistantConfiguration> hibernateBundle;
  
  public WebAssistantModule(WebAssistantConfiguration webAssistantConfiguration,HibernateBundle<WebAssistantConfiguration> hibernateBundle) {
    this.webAssistantConfiguration = webAssistantConfiguration;
    this.hibernateBundle = hibernateBundle;
  }
  
  @Override
  protected void configure() {
    
    bind(Webhook.class).to(ApiAiWebhook.class);
    bind(WelcomeStrategy.class).to(GroceryStoreWelcome.class);
    bind(AddToBasketStrategy.class).to(AddToGroceryBasket.class);
    bind(CheckoutStrategy.class).to(SimpleCheckout.class);
    
    bind(AiConversationService.class).to(TextConversation.class);
    bind(UserService.class).to(UserManager.class);
    
    bind(IUserDao.class).to(UserDao.class);
  }
  
  @Provides
  @Singleton
  public SessionFactory providesSessionFactory()
  {
    return hibernateBundle.getSessionFactory();
  }
}
