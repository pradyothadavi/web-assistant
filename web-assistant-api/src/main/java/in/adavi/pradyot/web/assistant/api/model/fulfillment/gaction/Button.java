package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Button {
  
  private String title;
  
  private OpenUrlAction openUrlAction;
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public OpenUrlAction getOpenUrlAction() {
    return openUrlAction;
  }
  
  public void setOpenUrlAction(OpenUrlAction openUrlAction) {
    this.openUrlAction = openUrlAction;
  }
  
  @Override
  public String toString() {
    return "Button{" + "title='" + title + '\'' + ", openUrlAction=" + openUrlAction + '}';
  }
}
