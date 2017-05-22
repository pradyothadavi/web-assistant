package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown =  true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenUrlAction {
  
  private String url;
  
  public String getUrl() {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  @Override
  public String toString() {
    return "OpenUrlAction{" + "url='" + url + '\'' + '}';
  }
}
