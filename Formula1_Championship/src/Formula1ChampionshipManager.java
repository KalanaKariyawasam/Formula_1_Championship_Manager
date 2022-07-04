//import libraries
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {

    //Create arraylists & input object
    ArrayList<Formula1Driver> drivers = new ArrayList();
    ArrayList<Race> raceData = new ArrayList();
    static Scanner input = new Scanner(System.in);

    //MainMenu
    public void mainMenu() {
        System.out.println("\n------ F O R M U L A - 1 - C H A M P I O N S H I P ------");
        System.out.println("A: Create a New Driver.");
        System.out.println("B: Delete a Driver.");
        System.out.println("C: Change the Driver.");
        System.out.println("D: Add Statistics.");
        System.out.println("E: Display Statistics.");
        System.out.println("F: Display the Formula 1 Driver Table.");
        System.out.println("G: Add a Race Data.");
        System.out.println("H: Display the Formula 1 Championship Table");
        System.out.println("I: Save all the Information.");
        System.out.println("J: View Graphical User Interface.");
        System.out.println("X: Exit.");
        System.out.println("---------------------------------------------------------");
    }

    //Create Driver Method
    @Override
    public void createDriver() {
        while (true) {
            try {
                //Get Inputs
                System.out.print("\nEnter driver name : ");
                String name = input.next().toUpperCase();
                System.out.print("Enter driver team : ");
                String team = input.next().toUpperCase();
                System.out.print("Enter driver location : ");
                String location = input.next().toUpperCase();

                //Validate & Process
                if (drivers.size() == 0) {
                    drivers.add(new Formula1Driver(name, team, location));
                    System.out.println("\nDriver crated successfully..");
                }
                else {
                    for (int i = 0; i < drivers.size(); i++) {
                        if (name.equals(drivers.get(i).getName())) {
                            System.out.println("\nDriver name already existed!..");
                            break;
                        }
                        else if (team.equals(drivers.get(i).getTeam())) {
                            System.out.println("\nDriver team already existed!..");
                            break;
                        }
                        else {
                            drivers.add(new Formula1Driver(name, team, location));
                            System.out.println("\nDriver crated successfully..");
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("\nInvalid Input..");
            }
            System.out.print("\nDo you want to create an another driver? (y/n) : ");
            String choice = input.next().toLowerCase();
            if (choice.equals("y")) {
                continue;
            } else if (choice.equals("n")) {
                break;
            } else {
                System.out.println("\nInvalid input..");
                break;
            }
        }
    }

    //Delete Driver Method
    @Override
    public void deleteDriver() {
        while (true) {
            //Get Inputs
            System.out.print("\nEnter driver name you want to delete : ");
            String deleteName = input.next().toUpperCase();
            System.out.print("Enter driver team you want to delete : ");
            String deleteTeam = input.next().toUpperCase();

            //Validate & Process
            boolean notFound = true;
            for (int i = 0; i < drivers.size(); i++) {
                if ((drivers.get(i).getName().equals(deleteName)) && (drivers.get(i).getTeam().equals(deleteTeam))) {
                    drivers.remove(i);
                    System.out.println("\nDriver deleted successful..");
                    notFound = false;
                }
            }
            if (notFound) {
                System.out.println("\nInvalid inputs or inputs doesn't exist!");
            }

            System.out.print("Do you want to delete an another driver? (y/n) : ");
            String choice = input.next().toLowerCase();
            if (choice.equals("y")) {
                continue;
            } else if (choice.equals("n")) {
                break;
            } else {
                System.out.println("\nInvalid input..");
            }
        }
    }

    //Change Driver Method
    @Override
    public void changeDriver() {
        System.out.print("\nEnter your team name : ");
        String findTeam = input.next().toUpperCase();

        boolean error = true;
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getTeam().equals(findTeam)) {
                System.out.print("Enter new driver name you want to change : ");
                String changeName = input.next().toUpperCase();
                drivers.get(i).setName(changeName);
                System.out.println("\nDriver name changed successful..");
                error = false;
            }
        }
        if (error) {
            System.out.println("\nInvalid team name or team doesn't exist!");
        }
    }

    //Add Statistics Method
    @Override
    public void addStatistics() {
        while (true) {
            //Get Inputs
            System.out.print("\nEnter driver name you want add statistics : ");
            String statName = input.next().toUpperCase();

            //Validate and Process
            boolean notFound = true;
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getName().equals(statName)) {
                    System.out.print("Enter your position : ");
                    int position = input.nextInt();

                    //Update driver's statistics
                    drivers.get(i).numOfRaces += 1;
                    drivers.get(i).add_Statistics(position);
                    drivers.get(i).pointsCalc(position);
                    notFound = false;
                }
            }
            if (notFound) {
                System.out.println("\nDriver not existed! Try Again..");
            }

            System.out.print("Do you want to add an another statistics? (y/n) : ");
            String choice = input.next().toLowerCase();
            if (choice.equals("y")) {
                continue;
            } else if (choice.equals("n")) {
                break;
            } else {
                System.out.println("\nInvalid input..");
            }
        }
    }

    //Display Statistics Method
    @Override
    public void displayStatistics() {
        //Get Inputs
        System.out.print("\nEnter driver name to see the statistics : ");
        String statName = input.next().toUpperCase();

        //Display stats of a driver
        boolean notFound = true;
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getName().equals(statName)) {
                System.out.println("\nDriver Name : " + drivers.get(i).getName());
                System.out.println("First Positions : " + drivers.get(i).firstPositions);
                System.out.println("Second Positions : " + drivers.get(i).secondPositions);
                System.out.println("Third Positions : " + drivers.get(i).thirdPositions);
                System.out.println("Number of Points : " + drivers.get(i).numOfPoints);
                System.out.println("Number of Races : " + drivers.get(i).numOfRaces);
                notFound = false;
            }
        }
        if (notFound) {
            System.out.println("\nDriver not existed! Try Again..");
        }
    }

    //Display Driver Table Method
    @Override
    public void displayDriverTable() {
        //Get drivers data and display in a table
        Collections.sort(drivers, new PointsDescending());
        String leftAlignFormat = "| %-11s | %-13s | %-13d | %-13d | %-13d | %-12d | %-12d |%n";

        System.out.format("+-----------------------------------------------------------------------------------------------------------+%n");
        System.out.format("|                  F O R M U L A - 1 - C H A M P I O N S H I P    D R I V E R   T A B L E                   |%n");
        System.out.format("+-------------+---------------+---------------+---------------+---------------+--------------+--------------+%n");
        System.out.format("| Driver Name |   Team Name   | 1st Positions | 2nd Positions | 3rd Positions | R. Completed | Total Points |%n");
        System.out.format("+-------------+---------------+---------------+---------------+---------------+--------------+--------------+%n");
        for (int i = 0; i < drivers.size(); i++) {
            System.out.format(leftAlignFormat, drivers.get(i).getName(), drivers.get(i).getTeam(), drivers.get(i).firstPositions, drivers.get(i).secondPositions, drivers.get(i).thirdPositions, drivers.get(i).numOfRaces, drivers.get(i).numOfPoints);
        }
        System.out.format("+-------------+---------------+---------------+---------------+---------------+--------------+--------------+%n");
    }

    //Add Race Method
    @Override
    public void addRace() {
        if(drivers.size() >= 10 ) {
            if(drivers.size() != 0) {
                ArrayList<String> racePositionName = new ArrayList();

                //Get Inputs
                System.out.print("\nEnter race date (yyyy-MM-dd) : ");
                String date = input.next();
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                boolean notFound = true;

                //Get Inputs and Process
                for (int i = 0; i < 10; i++) {
                    while (true) {
                        System.out.print("Enter the name of position " + (i + 1) + " : ");
                        String name = input.next().toUpperCase();
                        int position = (i + 1);

                        for (int j = 0; j < drivers.size(); j++) {
                            if (name.equals(drivers.get(j).getName())) {
                                racePositionName.add(name);
                                drivers.get(j).add_Statistics(position); //Update drivers stats
                                drivers.get(j).pointsCalc(position);
                                drivers.get(j).numOfRaces += 1;
                                notFound = false;
                            }
                        }
                        if (notFound) {
                            System.out.println("\nInvalid inputs or inputs doesn't exist!");
                            continue;
                        }
                        notFound = true;
                        break;
                    }
                }
                raceData.add(new Race(localDate, racePositionName));
                System.out.println("\nRace added successfully..");
            }
            else{
                System.out.println("\nDrivers isn't created... Create Drivers First!..");
            }
        }
        else {
            System.out.println("\nTo add a race there should be atleast 10 drivers..");
        }
    }

    //Display Championship Table Method
    @Override
    public void displayChampionshipTable() {
        String leftAlignFormat = "| %-18s | %-18s | %-18s |%n";

        System.out.format("+--------------------------------------------------------------+%n");
        System.out.format("|   F O R M U L A - 1 - C H A M P I O N S H I P   T A B L E    |%n");
        System.out.format("+--------------------+--------------------+--------------------+%n");
        System.out.format("|        Date        |      Position      |     Driver Name    |%n");
        System.out.format("+--------------------+--------------------+--------------------+%n");
        for (int i = 0; i < raceData.size(); i++) {
            System.out.format(leftAlignFormat, raceData.get(i).getDate(), " ", " ");
            for (int j = 0; j < 10; j++) {
                String k = String.valueOf(j + 1);
                System.out.format(leftAlignFormat, " ", k, raceData.get(i).getPositionName().get(j));
            }
        }
        System.out.format("+--------------------+--------------------+--------------------+%n");
    }
}
