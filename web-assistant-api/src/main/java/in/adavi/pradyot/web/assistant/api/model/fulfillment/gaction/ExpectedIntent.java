package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpectedIntent {
  
  private String intent;
  
  private InputValueData inputValueData;
  
  public String getIntent() {
    return intent;
  }
  
  public void setIntent(String intent) {
    this.intent = intent;
  }
  
  public InputValueData getInputValueData() {
    return inputValueData;
  }
  
  public void setInputValueData(InputValueData inputValueData) {
    this.inputValueData = inputValueData;
  }
  
  @Override
  public String toString() {
    return "ExpectedIntent{" + "intent='" + intent + '\'' + ", inputValueData=" + inputValueData + '}';
  }
}
