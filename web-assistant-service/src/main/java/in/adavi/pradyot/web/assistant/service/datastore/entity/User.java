package in.adavi.pradyot.web.assistant.service.datastore.entity;

/**
 * Created by pradyot.ha on 17/05/17.
 */
public class User {
  
  private String userId;
  
  private String firstName;
  
  private String lastName;
  
  private String gender;
  
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
  
  public String getGender() {
    return gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;
  }
  
  @Override
  public String toString() {
    return "User{" + "userId='" + userId + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", gender='" + gender + '\'' + '}';
  }
}
