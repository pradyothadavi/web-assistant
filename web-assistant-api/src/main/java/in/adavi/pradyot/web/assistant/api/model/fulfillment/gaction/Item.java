package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
  
  private SimpleResponse simpleResponse;
  
  private BasicCard basicCard;
  
  private StructuredResponse structuredResponse;
  
  public SimpleResponse getSimpleResponse() {
    return simpleResponse;
  }
  
  public void setSimpleResponse(SimpleResponse simpleResponse) {
    this.simpleResponse = simpleResponse;
  }
  
  public BasicCard getBasicCard() {
    return basicCard;
  }
  
  public void setBasicCard(BasicCard basicCard) {
    this.basicCard = basicCard;
  }
  
  public StructuredResponse getStructuredResponse() {
    return structuredResponse;
  }
  
  public void setStructuredResponse(StructuredResponse structuredResponse) {
    this.structuredResponse = structuredResponse;
  }
  
  @Override
  public String toString() {
    return "Item{" + "simpleResponse=" + simpleResponse + ", basicCard=" + basicCard + ", structuredResponse=" + structuredResponse + '}';
  }
}
