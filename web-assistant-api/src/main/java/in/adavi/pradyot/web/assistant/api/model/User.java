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
  
  public Gender getGender() {
    return gender;
  }
  
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  @Override
  public String toString() {
    return "User{" + "userId='" + userId + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", gender=" + gender + '}';
  }
}
