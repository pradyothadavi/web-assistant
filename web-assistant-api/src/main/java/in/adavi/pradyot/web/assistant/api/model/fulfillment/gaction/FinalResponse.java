package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinalResponse {

  private RichResponse richResponse;
  
  public RichResponse getRichResponse() {
    return richResponse;
  }
  
  public void setRichResponse(RichResponse richResponse) {
    this.richResponse = richResponse;
  }
  
  @Override
  public String toString() {
    return "FinalResponse{" + "richResponse=" + richResponse + '}';
  }
}
