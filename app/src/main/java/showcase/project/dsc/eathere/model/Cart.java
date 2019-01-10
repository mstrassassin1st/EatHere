package showcase.project.dsc.eathere.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private int menuID;
    private String menuName;
    private int menuPrice;
    private int menuQuantity = 0;

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menID) {
        this.menuID = menID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getMenuQuantity() {
        return menuQuantity;
    }

    public void setMenuQuantity(int menuQuantity) {
        this.menuQuantity = menuQuantity;
    }
}
