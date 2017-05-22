package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppResponse {
  
  private String conversationToken;
  
  private boolean expectUserResponse;
  
  private List<ExpectedInput> expectedInputs;
  
  private FinalResponse finalResponse;
  
  private CustomPushMessage customPushMessage;
  
  private ResponseMetaData responseMetadata;
  
  public String getConversationToken() {
    return conversationToken;
  }
  
  public void setConversationToken(String conversationToken) {
    this.conversationToken = conversationToken;
  }
  
  public boolean isExpectUserResponse() {
    return expectUserResponse;
  }
  
  public void setExpectUserResponse(boolean expectUserResponse) {
    this.expectUserResponse = expectUserResponse;
  }
  
  public List<ExpectedInput> getExpectedInputs() {
    return expectedInputs;
  }
  
  public void setExpectedInputs(List<ExpectedInput> expectedInputs) {
    this.expectedInputs = expectedInputs;
  }
  
  public FinalResponse getFinalResponse() {
    return finalResponse;
  }
  
  public void setFinalResponse(FinalResponse finalResponse) {
    this.finalResponse = finalResponse;
  }
  
  public CustomPushMessage getCustomPushMessage() {
    return customPushMessage;
  }
  
  public void setCustomPushMessage(CustomPushMessage customPushMessage) {
    this.customPushMessage = customPushMessage;
  }
  
  public ResponseMetaData getResponseMetadata() {
    return responseMetadata;
  }
  
  public void setResponseMetadata(ResponseMetaData responseMetadata) {
    this.responseMetadata = responseMetadata;
  }
  
  @Override
  public String toString() {
    return "AppResponse{" + "conversationToken='" + conversationToken + '\'' + ", expectUserResponse=" + expectUserResponse + ", expectedInputs=" + expectedInputs + ", finalResponse=" + finalResponse + ", customPushMessage=" + customPushMessage + ", responseMetadata=" + responseMetadata + '}';
  }
}
