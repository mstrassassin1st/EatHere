package showcase.project.dsc.eathere.Data;

public class RestaurantData {
    private String name;
    private String description;
    private int id;

    public  RestaurantData(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setId(int id) { this.id = id;}

    public int getId() { return id;}

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
