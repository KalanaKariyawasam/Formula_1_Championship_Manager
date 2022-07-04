//import libraries
import java.util.Comparator;

public class PointsAscending implements Comparator {

    @Override
    //Sort by ascending order of points
    public int compare(Object o1, Object o2) {
        Formula1Driver driver1 = (Formula1Driver) o1;
        Formula1Driver driver2 = (Formula1Driver) o2;

        if(driver1.getNumOfPoints() == driver2.getNumOfPoints())
            return 0;
        else if(driver1.getNumOfPoints() > driver2.getNumOfPoints())
            return 1;
        else
            return -1;
    }
}

