package in.adavi.pradyot.web.assistant.api.model.fulfillment.facebook;

/**
 * Created by pradyot.ha on 19/05/17.
 */
public enum ContentType {
  
  LOCATION("location"),
  TEXT("text");
  
  String contentType;
  
  ContentType(String contentType) {
    this.contentType = contentType;
  }
  
  public String getContentType() {
    return contentType;
  }
}
