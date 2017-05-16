package in.adavi.pradyot.web.assistant.service.datastore.entity;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class UnitWeight {
  
  private String unit;
  
  private Double amount;
  
  public String getUnit() {
    return unit;
  }
  
  public void setUnit(String unit) {
    this.unit = unit;
  }
  
  public Double getAmount() {
    return amount;
  }
  
  public void setAmount(Double amount) {
    this.amount = amount;
  }
  
  @Override
  public String toString() {
    return "UnitWeight{" + "unit='" + unit + '\'' + ", amount=" + amount + '}';
  }
}
