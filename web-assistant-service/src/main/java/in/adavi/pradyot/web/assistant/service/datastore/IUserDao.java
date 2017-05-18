package in.adavi.pradyot.web.assistant.service.datastore;

import in.adavi.pradyot.web.assistant.service.datastore.entity.User;
import in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference;

/**
 * Created by pradyot.ha on 17/05/17.
 */
public interface IUserDao {
  
  User fetchUser(String userId);
  
  UserPreference fetchUserPreference(String userId);
}
