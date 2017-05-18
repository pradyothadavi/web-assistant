package in.adavi.pradyot.web.assistant.service.datastore.entity;

/**
 * Created by pradyot.ha on 17/05/17.
 */
public class UserPreference {
  
  private String userId;
  
  private String emailId;
  
  private String phoneNo;
  
  private String language;
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
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
  
  public String getLanguage() {
    return language;
  }
  
  public void setLanguage(String language) {
    this.language = language;
  }
  
  @Override
  public String toString() {
    return "UserPreference{" + "userId='" + userId + '\'' + ", emailId='" + emailId + '\'' + ", phoneNo='" + phoneNo + '\'' + ", language='" + language + '\'' + '}';
  }
}
