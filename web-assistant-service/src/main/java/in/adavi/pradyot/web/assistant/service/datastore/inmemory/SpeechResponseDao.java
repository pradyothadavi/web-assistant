package in.adavi.pradyot.web.assistant.service.datastore.inmemory;

import in.adavi.pradyot.web.assistant.service.datastore.ISpeechResponseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class SpeechResponseDao implements ISpeechResponseDao {
  
  private static final Logger logger = LoggerFactory.getLogger(SpeechResponseDao.class);
  
  private Map<String,String> intentIdToSpeechTemplate;
  
  public SpeechResponseDao() {
    this.intentIdToSpeechTemplate = new HashMap<>();
    intentIdToSpeechTemplate.put("f036d86d-3538-4f9b-9b7a-620a369260ad","Added %s %s of %s to the basket.");
    intentIdToSpeechTemplate.put("10fc52d0-d00f-410f-bb0a-55b77d5105a5","Hey %s, Welcome to Flipkart Grocery Store. Start adding items to your grocery basket, and checkout using Flipkart Pay Later.");
  }
  
  @Override
  public String getSpeechTemplate(String intentId) {
    return intentIdToSpeechTemplate.get(intentId);
  }
}
