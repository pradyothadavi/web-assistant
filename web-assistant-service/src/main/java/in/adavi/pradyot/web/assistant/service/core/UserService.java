package in.adavi.pradyot.web.assistant.service.core;

import in.adavi.pradyot.web.assistant.api.model.User;
import in.adavi.pradyot.web.assistant.api.model.UserPreference;

/**
 * Created by pradyot.ha on 17/05/17.
 */
public interface UserService {
  
  User getUserById(String userId);
  
  UserPreference getPreference(User user);
}
