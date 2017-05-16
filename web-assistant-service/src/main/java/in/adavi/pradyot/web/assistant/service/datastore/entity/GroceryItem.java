package in.adavi.pradyot.web.assistant.service.datastore.entity;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class GroceryItem {
  
  private String listingId;
  
  private String itemName;
  
  private UnitWeight unitWeight;
  
  public String getListingId() {
    return listingId;
  }
  
  public void setListingId(String listingId) {
    this.listingId = listingId;
  }
  
  public String getItemName() {
    return itemName;
  }
  
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  
  public UnitWeight getUnitWeight() {
    return unitWeight;
  }
  
  public void setUnitWeight(UnitWeight unitWeight) {
    this.unitWeight = unitWeight;
  }
  
  @Override
  public String toString() {
    return "GroceryItem{" + "listingId='" + listingId + '\'' + ", itemName='" + itemName + '\'' + ", unitWeight=" + unitWeight + '}';
  }
}
