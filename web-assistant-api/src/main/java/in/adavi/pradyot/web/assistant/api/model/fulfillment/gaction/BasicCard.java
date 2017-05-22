package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicCard {

  private String title;
  
  private String subtitle;
  
  private String formattedText;
  
  private Image image;
  
  private List<Button> buttons;
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getSubtitle() {
    return subtitle;
  }
  
  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }
  
  public String getFormattedText() {
    return formattedText;
  }
  
  public void setFormattedText(String formattedText) {
    this.formattedText = formattedText;
  }
  
  public Image getImage() {
    return image;
  }
  
  public void setImage(Image image) {
    this.image = image;
  }
  
  public List<Button> getButtons() {
    return buttons;
  }
  
  public void setButtons(List<Button> buttons) {
    this.buttons = buttons;
  }
  
  @Override
  public String toString() {
    return "BasicCard{" + "title='" + title + '\'' + ", subtitle='" + subtitle + '\'' + ", formattedText='" + formattedText + '\'' + ", image=" + image + ", buttons=" + buttons + '}';
  }
}
