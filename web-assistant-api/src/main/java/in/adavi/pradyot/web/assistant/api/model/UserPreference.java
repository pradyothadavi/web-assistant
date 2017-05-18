package in.adavi.pradyot.web.assistant.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pradyot.ha on 17/05/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPreference {
  
  @JsonProperty("language")
  private Language language;
  
  @JsonProperty("email_id")
  private String emailId;
  
  @JsonProperty("phone_no")
  private String phoneNo;
  
  public Language getLanguage() {
    return language;
  }
  
  public void setLanguage(Language language) {
    this.language = language;
  }
  
  public String getEmailId() {
    return emailId;
  }
  
  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }
  
  public String getPhoneNo() {
    return phoneNo;
  }
  
  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }
  
  @Override
  public String toString() {
    return "UserPreference{" + "language=" + language + ", emailId='" + emailId + '\'' + ", phoneNo='" + phoneNo + '\'' + '}';
  }
}
