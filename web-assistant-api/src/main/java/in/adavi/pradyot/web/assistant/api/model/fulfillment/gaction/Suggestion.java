package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suggestion {
  
  private String title;
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  @Override
  public String toString() {
    return "Suggestion{" + "title='" + title + '\'' + '}';
  }
}
