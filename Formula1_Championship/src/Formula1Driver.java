//import libraries
import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {
    //Create instance variables
    public int firstPositions;
    public int secondPositions;
    public int thirdPositions;
    public int numOfPoints;
    public int numOfRaces;

    //Constructor
    public Formula1Driver(String name, String team, String location) {
        super(name, team, location);
        this.firstPositions = 0;
        this.secondPositions = 0;
        this.thirdPositions = 0;
        this.numOfPoints = 0;
        this.numOfRaces = 0;
    }

    //Getters
    public int getFirstPositions() {
        return firstPositions;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    //Calculate Points Method
    public void pointsCalc(int position){
        switch (position){
            case 1 :
                numOfPoints += 25;
                break;
            case 2 :
                numOfPoints += 18;
                break;
            case 3 :
                numOfPoints += 15;
                break;
            case 4 :
                numOfPoints += 12;
                break;
            case 5 :
                numOfPoints += 10;
                break;
            case 6 :
                numOfPoints += 8;
                break;
            case 7 :
                numOfPoints += 6;
                break;
            case 8 :
                numOfPoints += 4;
                break;
            case 9 :
                numOfPoints += 2;
                break;
            case 10 :
                numOfPoints += 1;
                break;
        }
    }

    //Position Update Method
    public void add_Statistics(int position) {

        if(position == 1){
            firstPositions += 1;
        }
        else if(position == 2){
            secondPositions += 1;
        }
        else if(position == 3){
            thirdPositions += 1;
        }
    }
}
