package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleResponse {
  
  private String textToSpeech;
  
  private String ssml;
  
  private String displayText;
  
  public String getTextToSpeech() {
    return textToSpeech;
  }
  
  public void setTextToSpeech(String textToSpeech) {
    this.textToSpeech = textToSpeech;
  }
  
  public String getSsml() {
    return ssml;
  }
  
  public void setSsml(String ssml) {
    this.ssml = ssml;
  }
  
  public String getDisplayText() {
    return displayText;
  }
  
  public void setDisplayText(String displayText) {
    this.displayText = displayText;
  }
  
  @Override
  public String toString() {
    return "SimpleResponse{" + "textToSpeech='" + textToSpeech + '\'' + ", ssml='" + ssml + '\'' + ", displayText='" + displayText + '\'' + '}';
  }
}