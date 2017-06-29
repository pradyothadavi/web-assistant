package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.Basket;
import com.github.pradyothadavi.Hackday;
import com.github.pradyothadavi.HackdayProduct;
import com.github.pradyothadavi.Product;
import com.github.pradyothadavi.api.ai.model.Result;
import com.github.pradyothadavi.api.ai.model.entity.AmountUnit;
import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.core.BasketService;
import com.github.pradyothadavi.core.ProductService;
import com.github.pradyothadavi.google.action.model.ExpectedInput;
import com.github.pradyothadavi.google.action.model.ExpectedIntent;
import com.github.pradyothadavi.google.action.model.InputPrompt;
import com.github.pradyothadavi.google.action.model.Item;
import com.github.pradyothadavi.google.action.model.RichResponse;
import com.github.pradyothadavi.google.action.model.SimpleResponse;
import com.github.pradyothadavi.google.action.response.AppResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.AddToBasketStrategy;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by pradyot.ha on 24/05/17.
 */
public class AddToGroceryBasket extends AddToBasketStrategy {
  
  private static final Logger logger = LoggerFactory.getLogger(AddToGroceryBasket.class);
  
  private static final String RESPONSE_TEMPLATE = "Added %s to basket. Next";
  private static final String STAPLE_ITEM_TEMPLATE = "%s %s of %s";
  private static final String ITEM_NOT_AVAILABLE = "Sorry, %s is currently not available. Next";
  
  private Client client;
  private WebTarget webTarget;
  
  @Inject
  private ProductService productService;
  
  @Inject
  private BasketService basketService;

  public ProductService getProductService() {
    return productService;
  }

  public void setProductService(ProductService productService) {
    this.productService = productService;
  }
  
  public BasketService getBasketService() {
    return basketService;
  }
  
  public void setBasketService(BasketService basketService) {
    this.basketService = basketService;
  }
  
  @Override
  protected boolean addToCart(QueryResponse queryResponse) {
    
    boolean isAddToCart = false;
  
    client = ClientBuilder.newClient();
    client.property(ClientProperties.CONNECT_TIMEOUT, 10000);
    client.property(ClientProperties.READ_TIMEOUT, 10000);
  
    this.webTarget = client.target("http://172.20.44.154:8180");
    
    Basket basket = basketService.get(queryResponse.getSessionId());
    if(null == basket)
    {
      basket = new Basket();
      basket.setName(queryResponse.getSessionId());
      basketService.create(basket);
    }
    
    Result result = queryResponse.getResult();
    Map<String,Object> parameters = result.getParameters();
  
    Product product = getProduct(parameters);
    if (product.isAvailable())
    {
      Hackday hackday = new Hackday();
      hackday.setUser("2.VI8D90AEFE012942FC85B1DABB415EA7FE.SI8F9904AE35FC406EA5BC92DF97D34080.VS47919AF4D991465DBB5A2F4ED0E9496D.1498558862");
  
      HackdayProduct hackdayProduct = new HackdayProduct();
      hackdayProduct.setName(product.getName());
      hackdayProduct.setBrand(product.getBrand());
      hackdayProduct.setUnit(product.getNoOfUnits());
      
      hackday.setHackdayProduct(hackdayProduct);
      
      basket.addProduct(product);
      basketService.update(basket);
      logger.info("Basket : {}",basketService.get(queryResponse.getSessionId()));
      isAddToCart = true;
  
      Response response = null;

//      try
//      {
//        response = webTarget.path("/bnpl-shylock/1/search").request(MediaType.APPLICATION_JSON)
//                     .post(Entity.entity(hackday, MediaType.APPLICATION_JSON));
//        if (Response.Status.OK.getStatusCode() == response.getStatus())
//        {
//
//        }
//      }
//      catch (Exception e)
//      {
//        if (null != response)
//        {
//          response.close();
//        }
//      }
    }
    return isAddToCart;
  }
  
  private Product getProduct(Map<String, Object> parameters) {
    Gson gson = new GsonBuilder().create();
    AmountUnit unitWeight = null;
    if(null != parameters.get("unit_weight"))
    {
      unitWeight = gson.fromJson(parameters.get("unit_weight").toString(), AmountUnit.class);
    }
    logger.info("Unit Weight : {}",unitWeight);
  
    AmountUnit unitVolume = null;
    if(null != parameters.get("unit_volume")){
      unitVolume = gson.fromJson(parameters.get("unit_volume").toString(), AmountUnit.class);
    }
    logger.info("Unit Volume : {}",unitVolume);
    String productName = null;
    if(null != parameters.get("grocery_item"))
    {
      productName = parameters.get("grocery_item").toString();
    }
    String brand = null;
    if(null != parameters.get("brand"))
    {
      brand = parameters.get("brand").toString();
    }
    Product product = new Product();
    product.setName(productName);
    product.setBrand(brand);
    product.setAvailable(true);
    product.setPricePerUnit(new Double(60));
    product.setNoOfUnits(1);
    product.setUnitType("unit");
  
    if(null != unitWeight)
    {
      product.setNoOfUnits(unitWeight.getAmount().intValue());
      product.setUnitType(unitWeight.getUnit());
    }
    if(null != unitVolume)
    {
      product.setNoOfUnits(unitVolume.getAmount().intValue());
      product.setUnitType(unitVolume.getUnit());
    }
    logger.info("Product : {}",product);
    return product;
  }
  
  @Override
  protected boolean verifyUser() {
    return true;
  }
  
  @Override
  public FulfillmentServiceResponse ask(QueryResponse queryResponse, boolean isAddToCart) {
    
    Result result = queryResponse.getResult();
    Map<String,Object> parameters = result.getParameters();
  
    Product product = getProduct(parameters);
    
    String responseMsg = null;
    if(!isAddToCart)
    {
      responseMsg = String.format(ITEM_NOT_AVAILABLE,product.getName());
    } else {
      String stapleItem = String.format(STAPLE_ITEM_TEMPLATE,String.valueOf(product.getNoOfUnits()),product.getUnitType(),product.getName());
      responseMsg = String.format(RESPONSE_TEMPLATE,product.getName());
    }
    SimpleResponse simpleResponse = new SimpleResponse();
    simpleResponse.setTextToSpeech(responseMsg);
    simpleResponse.setDisplayText(responseMsg);
    simpleResponse.setSsml(null);
    
    Item simpleItem = new Item();
    simpleItem.setSimpleResponse(simpleResponse);
    
    List<Item> items = new ArrayList<>();
    items.add(simpleItem);
    
    RichResponse richResponse = new RichResponse();
    richResponse.setItems(items);
  
    InputPrompt inputPrompt = new InputPrompt();
    inputPrompt.setRichInitialPrompt(richResponse);
  
    ExpectedIntent expectedIntent = new ExpectedIntent();
    expectedIntent.setIntent("actions.intent.TEXT");
  
    List<ExpectedIntent> expectedIntents = new ArrayList<>();
    expectedIntents.add(expectedIntent);
  
    ExpectedInput expectedInput = new ExpectedInput();
    expectedInput.setInputPrompt(inputPrompt);
    expectedInput.setPossibleIntents(expectedIntents);
  
    AppResponse appResponse = new AppResponse();
    appResponse.setExpectUserResponse(true);
    appResponse.setExpectedInputs(Arrays.asList(expectedInput));
  
    GoogleActionResponse googleActionResponse = new GoogleActionResponse();
    googleActionResponse.setData(appResponse);
    
    FulfillmentServiceResponse fulfillmentServiceResponse;
    fulfillmentServiceResponse = new FulfillmentServiceResponse();
    fulfillmentServiceResponse.setSpeech(simpleResponse.getTextToSpeech());
    fulfillmentServiceResponse.setDisplayText(simpleResponse.getDisplayText());
    fulfillmentServiceResponse.setSource("grocery-fulfillment");
    fulfillmentServiceResponse.setData(googleActionResponse);
    
    return fulfillmentServiceResponse;
  }
}
