package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {

  private String url;
  
  private String accessibilityText;
  
  private int height;
  
  private int width;
  
  public String getUrl() {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getAccessibilityText() {
    return accessibilityText;
  }
  
  public void setAccessibilityText(String accessibilityText) {
    this.accessibilityText = accessibilityText;
  }
  
  public int getHeight() {
    return height;
  }
  
  public void setHeight(int height) {
    this.height = height;
  }
  
  public int getWidth() {
    return width;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  @Override
  public String toString() {
    return "Image{" + "url='" + url + '\'' + ", accessibilityText='" + accessibilityText + '\'' + ", height=" + height + ", width=" + width + '}';
  }
}
