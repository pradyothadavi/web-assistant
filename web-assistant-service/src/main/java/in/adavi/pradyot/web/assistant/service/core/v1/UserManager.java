package in.adavi.pradyot.web.assistant.service.core.v1;

import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.api.model.Gender;
import in.adavi.pradyot.web.assistant.api.model.Language;
import in.adavi.pradyot.web.assistant.api.model.User;
import in.adavi.pradyot.web.assistant.api.model.UserPreference;
import in.adavi.pradyot.web.assistant.service.core.UserService;
import in.adavi.pradyot.web.assistant.service.datastore.IUserDao;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * Created by pradyot.ha on 17/05/17.
 */
public class UserManager implements UserService {
  
  private IUserDao iUserDao;
  
  @Inject
  public UserManager(IUserDao iUserDao) {
    this.iUserDao = iUserDao;
  }
  
  @Override
  public User getUserById(String userId) {
    if(StringUtils.isEmpty(userId))
      return null;
    in.adavi.pradyot.web.assistant.service.datastore.entity.User userTuple = iUserDao.fetchUser(userId);
    return adaptUserDaoToApi.apply(userTuple);
  }
  
  @Override
  public UserPreference getPreference(User user) {
    if(null == user)
      return null;
    in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference userPreferenceTuple =
      iUserDao.fetchUserPreference(user.getUserId());
    return adaptUserPreferenceDaoToApi.apply(userPreferenceTuple);
  }
  
  Function<User, in.adavi.pradyot.web.assistant.service.datastore.entity.User> adaptUserApiToDao = new Function<User, in.adavi.pradyot.web.assistant.service.datastore.entity.User>() {
    @Override
    public in.adavi.pradyot.web.assistant.service.datastore.entity.User apply(User user) {
      if(null == user)
        return null;
      in.adavi.pradyot.web.assistant.service.datastore.entity.User userDao = new in.adavi.pradyot.web.assistant.service.datastore.entity.User();
      userDao.setUserId(user.getUserId());
      userDao.setFirstName(user.getFirstName());
      userDao.setLastName(user.getLastName());
      userDao.setGender(user.getGender().name());
      return userDao;
    }
  };
  
  Function<in.adavi.pradyot.web.assistant.service.datastore.entity.User,User> adaptUserDaoToApi = new Function<in.adavi.pradyot.web.assistant.service.datastore.entity.User, User>() {
    @Override
    public User apply(in.adavi.pradyot.web.assistant.service.datastore.entity.User user) {
      if(null == user)
        return null;
      User userApi = new User();
      userApi.setUserId(user.getUserId());
      userApi.setFirstName(user.getFirstName());
      userApi.setLastName(user.getLastName());
      userApi.setGender(Gender.valueOf(user.getGender()));
      return userApi;
    }
  };
  
  Function<UserPreference, in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference> adaptUserPreferenceApiToDao = new Function<UserPreference, in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference>() {
    @Override
    public in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference apply(UserPreference userPreference) {
      if(null == userPreference)
        return null;
      in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference userPreferenceDao = new in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference();
      userPreferenceDao.setEmailId(userPreference.getEmailId());
      userPreferenceDao.setPhoneNo(userPreference.getPhoneNo());
      userPreferenceDao.setLanguage(userPreference.getLanguage().name());
      return userPreferenceDao;
    }
  };
  
  Function<in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference, UserPreference> adaptUserPreferenceDaoToApi = new Function<in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference, UserPreference>() {
    @Override
    public UserPreference apply(in.adavi.pradyot.web.assistant.service.datastore.entity.UserPreference userPreference) {
      if(null == userPreference)
        return null;
      UserPreference userPreferenceApi = new UserPreference();
      userPreferenceApi.setEmailId(userPreference.getEmailId());
      userPreferenceApi.setLanguage(Language.valueOf(userPreference.getLanguage()));
      userPreferenceApi.setPhoneNo(userPreference.getPhoneNo());
      return userPreferenceApi;
    }
  };
}
