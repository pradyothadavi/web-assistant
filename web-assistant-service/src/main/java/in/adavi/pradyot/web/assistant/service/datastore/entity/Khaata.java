package in.adavi.pradyot.web.assistant.service.datastore.entity;

/**
 * Created by pradyot.ha on 18/05/17.
 */
public class Khaata {
  
  private int creditAvailed;
  
  private int creditLimit;
  
  public int getCreditAvailed() {
    return creditAvailed;
  }
  
  public void setCreditAvailed(int creditAvailed) {
    this.creditAvailed = creditAvailed;
  }
  
  public int getCreditLimit() {
    return creditLimit;
  }
  
  public void setCreditLimit(int creditLimit) {
    this.creditLimit = creditLimit;
  }
  
  @Override
  public String toString() {
    return "Khaata{" + "creditAvailed=" + creditAvailed + ", creditLimit=" + creditLimit + '}';
  }
}
