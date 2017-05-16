package in.adavi.pradyot.web.assistant.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Represents the user using the application
 *
 * @author pradyot.ha
 */
public class User {
  @NotNull
  @JsonProperty("user_id")
  private String userId;
  
  @NotNull
  @JsonProperty("first_name")
  private String firstName;
  
  @JsonProperty("last_name")
  private String lastName;
  
  @JsonProperty("email_id")
  private String emailId;
  
  @JsonProperty("phone_no")
  private String phoneNo;
  
  @JsonProperty("gender")
  private Gender gender;
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
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
  
  public Gender getGender() {
    return gender;
  }
  
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("userId='").append(userId).append('\'');
    sb.append(", firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append(", emailId='").append(emailId).append('\'');
    sb.append(", phoneNo='").append(phoneNo).append('\'');
    sb.append(", gender=").append(gender);
    sb.append('}');
    return sb.toString();
  }
}
