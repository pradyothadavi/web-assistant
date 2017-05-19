package in.adavi.pradyot.web.assistant.api.model.fulfillment.facebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by pradyot.ha on 19/05/17.
 */
public class Message {

  @JsonProperty("text")
  private String text;
  
  @JsonProperty("quick_replies")
  private List<QuickReply> quickReplies;
  
  @JsonIgnore
  public String getText() {
    return text;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  @JsonIgnore
  public List<QuickReply> getQuickReplies() {
    return quickReplies;
  }
  
  public void setQuickReplies(List<QuickReply> quickReplies) {
    this.quickReplies = quickReplies;
  }
  
  @Override
  public String toString() {
    return "Message{" + "text='" + text + '\'' + ", quickReplies=" + quickReplies + '}';
  }
}
