//import libraries
import java.io.Serializable;

public abstract class Driver implements Serializable {
    //Create Variables
    public String name;
    public String team;
    public String location;

    //Constructor
    public Driver(String name, String team, String location) {
        this.name = name;
        this.team = team;
        this.location = location;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }
}
