package showcase.project.dsc.eathere.model;

public class Menu {
    private int menuID;
    private int restaurantID;
    private String menuName;
    private String menuDescription;
    private int menuPrice;
    private int menuStock;
    private String menuStockStatus;

    private String menuStockStatus(int menuStatus){
        if (menuStatus == 0){
            return "Not Avaliable";
        }
        return "Avaliable";
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getMenuStock() {
        return menuStock;
    }

    public void setMenuStock(int menuStock) {
        this.menuStock = menuStock;
    }
}
