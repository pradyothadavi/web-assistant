package in.adavi.pradyot.web.assistant.api.model.fulfillment.gaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by pradyot.ha on 22/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RichResponse {
  
  private List<Item> items;
  
  private List<Suggestion> suggestions;
  
  private LinkOutSuggestion linkOutSuggestion;
  
  public List<Item> getItems() {
    return items;
  }
  
  public void setItems(List<Item> items) {
    this.items = items;
  }
  
  public List<Suggestion> getSuggestions() {
    return suggestions;
  }
  
  public void setSuggestions(List<Suggestion> suggestions) {
    this.suggestions = suggestions;
  }
  
  public LinkOutSuggestion getLinkOutSuggestion() {
    return linkOutSuggestion;
  }
  
  public void setLinkOutSuggestion(LinkOutSuggestion linkOutSuggestion) {
    this.linkOutSuggestion = linkOutSuggestion;
  }
  
  @Override
  public String toString() {
    return "RichResponse{" + "items=" + items + ", suggestions=" + suggestions + ", linkOutSuggestion=" + linkOutSuggestion + '}';
  }
}
