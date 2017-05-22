package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMetaData {
  
  private Status status;
  
  public Status getStatus() {
    return status;
  }
  
  public void setStatus(Status status) {
    this.status = status;
  }
  
  @Override
  public String toString() {
    return "ResponseMetaData{" + "status=" + status + '}';
  }
}
