package in.adavi.pradyot.web.assistant.api.model;

/**
 * Created by pradyot.ha on 17/05/17.
 */
public enum Language {
  
  English("en"),
  Gujarati("gu"),
  Hindi("hi"),
  Kannada("kn"),
  Marathi("mr"),
  Telugu("te"),
  Tamil("ta"),
  Malayalam("ml");
  
  Language(String languageTag) {
    this.languageTag = languageTag;
  }
  
  private String languageTag;
  
  public String getLanguageTag() {
    return this.languageTag;
  }
  
  public static Language getDefaultLanguage(){
    return English;
  }
}
