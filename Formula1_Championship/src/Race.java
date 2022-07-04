//import libraries
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Race implements Serializable {
    //instance variables
    public LocalDate date;
    ArrayList<String> positionName;

    //constructor
    public Race(LocalDate date, ArrayList<String> positionName) {
        this.date = date;
        this.positionName = positionName;
    }

    //Getters
    public LocalDate getDate() {
        return date;
    }

    public ArrayList<String> getPositionName() {
        return positionName;
    }
}
