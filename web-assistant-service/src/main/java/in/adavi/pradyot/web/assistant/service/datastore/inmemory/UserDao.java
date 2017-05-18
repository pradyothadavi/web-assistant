package in.adavi.pradyot.web.assistant.service.datastore.inmemory;

import in.adavi.pradyot.web.assistant.service.datastore.IUserDao;
import in.adavi.pradyot.web.assistant.service.datastore.entity.User;
import in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pradyot.ha on 17/05/17.
 */
public class UserDao implements IUserDao {
  
  private Map<String,User> userMap;
  private Map<String,UserPreference> userPreferenceMap;
  
  public UserDao() {
    
    User pradyot = new User();
    pradyot.setUserId("Pradyot");
    pradyot.setFirstName("Pradyot");
    pradyot.setLastName("Adavi");
    pradyot.setGender("MALE");
    
    UserPreference pradyotPreference = new UserPreference();
    pradyotPreference.setUserId("Pradyot");
    pradyotPreference.setLanguage("Kannada");
  
    User suyash = new User();
    suyash.setUserId("Suyash");
    suyash.setFirstName("Suyash");
    suyash.setLastName("Motarwar");
    suyash.setGender("MALE");
  
    UserPreference suyashPreference = new UserPreference();
    suyashPreference.setUserId("Suyash");
    suyashPreference.setLanguage("Marathi");
  
    User mayank = new User();
    mayank.setUserId("Mayank");
    mayank.setFirstName("Mayank");
    mayank.setLastName("Jain");
    mayank.setGender("MALE");
  
    UserPreference mayankPreference = new UserPreference();
    mayankPreference.setUserId("Mayank");
    mayankPreference.setLanguage("Gujarati");
    
    User amar = new User();
    amar.setUserId("Amar");
    amar.setFirstName("Amar");
    amar.setLastName("Nagaram");
    amar.setGender("MALE");
    
    UserPreference amarPreference = new UserPreference();
    amarPreference.setUserId("Amar");
    amarPreference.setLanguage("Telugu");
    
    this.userMap = new HashMap<>();
    this.userMap.put("Pradyot",pradyot);
    this.userMap.put("Suyash",suyash);
    this.userMap.put("Mayank",mayank);
    this.userMap.put("Amar",amar);
    
    this.userPreferenceMap = new HashMap<>();
    this.userPreferenceMap.put("Pradyot",pradyotPreference);
    this.userPreferenceMap.put("Suyash",suyashPreference);
    this.userPreferenceMap.put("Mayank",mayankPreference);
    this.userPreferenceMap.put("Amar",amarPreference);
  }
  
  @Override
  public User fetchUser(String userId) {
    return this.userMap.get(userId);
  }
  
  @Override
  public UserPreference fetchUserPreference(String userId) {
    return this.userPreferenceMap.get(userId);
  }
}
