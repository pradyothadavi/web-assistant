package in.adavi.pradyot.web.assistant.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class Chat {
  
  @NotNull
  @JsonProperty("user")
  private User user;
  
  @JsonProperty("message")
  private String message;
  
  @JsonProperty("response")
  private String recipientResponse;
  
  @JsonProperty("sessionId")
  private String sessionId;
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getRecipientResponse() {
    return recipientResponse;
  }
  
  public void setRecipientResponse(String recipientResponse) {
    this.recipientResponse = recipientResponse;
  }
  
  public String getSessionId() {
    return sessionId;
  }
  
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
  
  @Override
  public String toString() {
    return "Chat{" + "user=" + user + ", message='" + message + '\'' + ", recipientResponse='" + recipientResponse + '\'' + ", sessionId='" + sessionId + '\'' + '}';
  }
}
