//import libraries
import java.util.Comparator;

public class PositionDescending implements Comparator {

    @Override
    //Sort by descending order of position
    public int compare(Object o1, Object o2) {
        Formula1Driver driver1 = (Formula1Driver) o1;
        Formula1Driver driver2 = (Formula1Driver) o2;

        if(driver1.getFirstPositions() == driver2.getFirstPositions())
            return 0;
        else if(driver1.getFirstPositions() < driver2.getFirstPositions())
            return 1;
        else
            return -1;
    }
}

