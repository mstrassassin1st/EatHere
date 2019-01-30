package showcase.project.dsc.eathere;

public class DataPassing {
    private static DataPassing instance;
    private String username;
    private String email;
    private int eatpoints;
    private int eatcash;
    private int userID;


    public static DataPassing getInstance(){
        if (instance == null){
            instance = new DataPassing();
        }
        return instance;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEatpoints() {
        return eatpoints;
    }

    public void setEatpoints(int eatpoints) {
        this.eatpoints = eatpoints;
    }

    public int getEatcash() {
        return eatcash;
    }

    public void setEatcash(int eatcash) {
        this.eatcash = eatcash;
    }
}
