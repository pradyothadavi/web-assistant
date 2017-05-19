package in.adavi.pradyot.web.assistant.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pradyot.ha on 19/05/17.
 */
public class FacebookResponse {
  
  @JsonProperty("data")
  private Object data;
  
  public Object getData() {
    return data;
  }
  
  public void setData(Object data) {
    this.data = data;
  }
  
  @Override
  public String toString() {
    return "FacebookResponse{" + "data=" + data + '}';
  }
}
