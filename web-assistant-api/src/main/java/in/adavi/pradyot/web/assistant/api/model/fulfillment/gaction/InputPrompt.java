package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputPrompt {

  private RichResponse richInitialPrompt;
  
  public RichResponse getRichInitialPrompt() {
    return richInitialPrompt;
  }
  
  public void setRichInitialPrompt(RichResponse richInitialPrompt) {
    this.richInitialPrompt = richInitialPrompt;
  }
  
  @Override
  public String toString() {
    return "InputPrompt{" + "richInitialPrompt=" + richInitialPrompt + '}';
  }
}
