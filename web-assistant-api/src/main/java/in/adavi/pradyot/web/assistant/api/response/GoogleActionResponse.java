package in.adavi.pradyot.web.assistant.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleActionResponse {
  
  @JsonProperty("google")
  private Object data;
  
  @JsonIgnore
  public Object getData() {
    return data;
  }
  
  public void setData(Object data) {
    this.data = data;
  }
  
  @Override
  public String toString() {
    return "GoogleActionResponse{" + "data=" + data + '}';
  }
}
