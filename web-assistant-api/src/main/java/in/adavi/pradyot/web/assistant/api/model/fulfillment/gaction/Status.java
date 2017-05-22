package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
  
  private int code;
  
  private String message;
  
  public int getCode() {
    return code;
  }
  
  public void setCode(int code) {
    this.code = code;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  @Override
  public String toString() {
    return "Status{" + "code=" + code + ", message='" + message + '\'' + '}';
  }
}
