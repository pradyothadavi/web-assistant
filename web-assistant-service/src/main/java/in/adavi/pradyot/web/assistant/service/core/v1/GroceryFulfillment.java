package in.adavi.pradyot.web.assistant.service.core.v1;


import com.github.pradyothadavi.model.Context;
import com.github.pradyothadavi.model.Result;
import com.github.pradyothadavi.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.response.QueryResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.api.model.fulfillment.facebook.ContentType;
import in.adavi.pradyot.web.assistant.api.model.fulfillment.facebook.Message;
import in.adavi.pradyot.web.assistant.api.model.fulfillment.facebook.QuickReply;
import in.adavi.pradyot.web.assistant.api.response.FacebookResponse;
import in.adavi.pradyot.web.assistant.service.core.FulfillmentService;
import in.adavi.pradyot.web.assistant.service.datastore.IGroceryFulfillmentDao;
import in.adavi.pradyot.web.assistant.service.datastore.ISpeechResponseDao;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryItem;
import in.adavi.pradyot.web.assistant.service.datastore.entity.GroceryList;
import in.adavi.pradyot.web.assistant.service.datastore.entity.Khaata;
import in.adavi.pradyot.web.assistant.service.datastore.entity.UnitWeight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class GroceryFulfillment implements FulfillmentService {
  
  private static final Logger logger = LoggerFactory.getLogger(GroceryFulfillment.class);
  
  private static List<String> nonAvailableGroceryItems;
  
  private IGroceryFulfillmentDao iGroceryFulfillmentDao;
  private ISpeechResponseDao iSpeechResponseDao;
  
  static {
    nonAvailableGroceryItems = new ArrayList<>();
    nonAvailableGroceryItems.add("Sugar");
    nonAvailableGroceryItems.add("Toor dal");
  }
  
  @Inject
  public GroceryFulfillment(IGroceryFulfillmentDao iGroceryFulfillmentDao, ISpeechResponseDao iSpeechResponseDao) {
    this.iGroceryFulfillmentDao = iGroceryFulfillmentDao;
    this.iSpeechResponseDao = iSpeechResponseDao;
  }
  
  @Override
  public FulfillmentServiceResponse processFulfillmentRequest(QueryResponse queryResponse) {
  
    FulfillmentServiceResponse fulfillmentServiceResponse = null;
    Result result = queryResponse.getResult();
    String action = result.getAction();
    List<Context> contexts = result.getContexts();
    Context userContext = getUserContext(contexts);
    String speechTemplate;
    String speech;
    
    switch (action){
      
      case "checkout":
        iGroceryFulfillmentDao.checkoutGroceryList(userContext.getParameters().get("firstName").toString());
        iGroceryFulfillmentDao.debitKhaata(userContext.getParameters().get("firstName").toString());
        Khaata khaata = iGroceryFulfillmentDao.getGroceryKhaata(userContext.getParameters().get("firstName").toString());
        
        speech = "Your order is confirmed. So far you have availed INR "+khaata.getCreditAvailed()+" credit out of INR "+khaata.getCreditLimit();
        fulfillmentServiceResponse = new FulfillmentServiceResponse();
        fulfillmentServiceResponse.setSpeech(speech);
        fulfillmentServiceResponse.setDisplayText(speech);
        fulfillmentServiceResponse.setSource("grocery-fulfillment");
        fulfillmentServiceResponse.setData(null);
        break;
        
      case "add-item":
        GroceryList groceryList = iGroceryFulfillmentDao.getList("default");
        if(null != userContext){
          groceryList = iGroceryFulfillmentDao.getList(userContext.getParameters().get("firstName").toString());
        }
        Map<String,Object> parameters = result.getParameters();
        String item = parameters.get("grocery-item").toString();
        if(nonAvailableGroceryItems.contains(item))
        {
          speech = "Hey, "+item+" is currently out of stock. I will notify you once it is back in stock.";
        } else {
          GroceryItem groceryItem = new GroceryItem();
          groceryItem.setItemName(item);
          groceryItem.setListingId(item);
          String unitWeightInString = parameters.get("unit-weight").toString();
          UnitWeight unitWeight = null;
  
          Gson gson = new GsonBuilder().create();
          unitWeight = gson.fromJson(unitWeightInString,UnitWeight.class);
          groceryItem.setUnitWeight(unitWeight);
  
          iGroceryFulfillmentDao.addItem(groceryItem,groceryList);
  
          speechTemplate = iSpeechResponseDao.getSpeechTemplate(result.getMetadata().getIntentId());
          speech = String.format(speechTemplate,String.valueOf(groceryItem.getUnitWeight().getAmount()),groceryItem.getUnitWeight().getUnit(),groceryItem.getItemName());
        }
        
        
        fulfillmentServiceResponse = new FulfillmentServiceResponse();
        fulfillmentServiceResponse.setSpeech(speech);
        fulfillmentServiceResponse.setDisplayText(speech);
        fulfillmentServiceResponse.setSource("grocery-fulfillment");
        fulfillmentServiceResponse.setData(groceryList);
        break;
        
      case "input.welcome":
        if(null != userContext){
          iGroceryFulfillmentDao.createList(userContext.getParameters().get("firstName").toString());
          
          speechTemplate = iSpeechResponseDao.getSpeechTemplate(result.getMetadata().getIntentId());
          speech = String.format(speechTemplate,userContext.getParameters().get("firstName"));
  
          QuickReply quickReply1 = new QuickReply();
          quickReply1.setContentType(ContentType.TEXT.getContentType());
          quickReply1.setTitle("Quick Reply 01");
  
          QuickReply quickReply2 = new QuickReply();
          quickReply2.setContentType(ContentType.TEXT.getContentType());
          quickReply2.setTitle("Quick Reply 02");
          
          List<QuickReply> quickReplies = new ArrayList<>();
          quickReplies.add(quickReply1);
          quickReplies.add(quickReply2);
          
          Message message = new Message();
          message.setText(speech);
          message.setQuickReplies(quickReplies);
          
          FacebookResponse facebookResponse = new FacebookResponse();
          facebookResponse.setData(message);
          
          fulfillmentServiceResponse = new FulfillmentServiceResponse();
          fulfillmentServiceResponse.setSpeech(speech);
          fulfillmentServiceResponse.setDisplayText(speech);
          fulfillmentServiceResponse.setSource("grocery-fulfillment");
          fulfillmentServiceResponse.setData(facebookResponse);
        }
        break;
        
      default:
        fulfillmentServiceResponse = new FulfillmentServiceResponse();
        fulfillmentServiceResponse.setSpeech("Sorry I could not understand that. Can you elaborate ?");
        fulfillmentServiceResponse.setDisplayText("Sorry I could not understand that. Can you elaborate ?");
        fulfillmentServiceResponse.setSource("grocery-fulfillment");
        fulfillmentServiceResponse.setData(null);
    }
    return fulfillmentServiceResponse;
  }
  
  private Context getUserContext(List<Context> contexts) {
    for (Context context:
         contexts) {
      if("user-context".equals(context.getName())){
        return context;
      }
    }
    return null;
  }
}
