package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpectedInput {

  private InputPrompt inputPrompt;
  
  private List<ExpectedIntent> possibleIntents;
  
  private String speechBiasingHints;
  
  public InputPrompt getInputPrompt() {
    return inputPrompt;
  }
  
  public void setInputPrompt(InputPrompt inputPrompt) {
    this.inputPrompt = inputPrompt;
  }
  
  public List<ExpectedIntent> getPossibleIntents() {
    return possibleIntents;
  }
  
  public void setPossibleIntents(List<ExpectedIntent> possibleIntents) {
    this.possibleIntents = possibleIntents;
  }
  
  public String getSpeechBiasingHints() {
    return speechBiasingHints;
  }
  
  public void setSpeechBiasingHints(String speechBiasingHints) {
    this.speechBiasingHints = speechBiasingHints;
  }
  
  @Override
  public String toString() {
    return "ExpectedInput{" + "inputPrompt=" + inputPrompt + ", possibleIntents=" + possibleIntents + ", speechBiasingHints='" + speechBiasingHints + '\'' + '}';
  }
}
