package in.adavi.pradyot.web.assistant.api.model.fulfillment.facebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pradyot.ha on 20/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Messaging {
  
  @JsonProperty("message")
  private Message message;
  
  @JsonIgnore
  public Message getMessage() {
    return message;
  }
  
  public void setMessage(Message message) {
    this.message = message;
  }
  
  @Override
  public String toString() {
    return "Messaging{" + "message=" + message + '}';
  }
}
