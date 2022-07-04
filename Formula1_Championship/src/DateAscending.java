//import libraries
import java.util.Comparator;

public class DateAscending implements Comparator {

    @Override
    //Sort by ascending order of dates
    public int compare(Object o1, Object o2) {
        Race race1 = (Race) o1;
        Race race2 = (Race) o2;

        if(race1.getDate() == race2.getDate())
            return 0;
//        else if(race1.getDate() > race2.getDate())
//            return 1;
        else
            return -1;
    }
}

