package in.adavi.pradyot.web.assistant.api.model.fulfillment.facebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pradyot.ha on 19/05/17.
 */
public class QuickReply {
  
  @JsonProperty("content_type")
  private String contentType;
  
  @JsonProperty("title")
  private String title;
  
  @JsonProperty("payload")
  private String payload;
  
  @JsonProperty("image_url")
  private String imageUrl;
  
  @JsonIgnore
  public String getContentType() {
    return contentType;
  }
  
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  
  @JsonIgnore
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  @JsonIgnore
  public String getPayload() {
    return payload;
  }
  
  public void setPayload(String payload) {
    this.payload = payload;
  }
  
  @JsonIgnore
  public String getImageUrl() {
    return imageUrl;
  }
  
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  
  @Override
  public String toString() {
    return "QuickReply{" + "contentType='" + contentType + '\'' + ", title='" + title + '\'' + ", payload='" + payload + '\'' + ", imageUrl='" + imageUrl + '\'' + '}';
  }
}
