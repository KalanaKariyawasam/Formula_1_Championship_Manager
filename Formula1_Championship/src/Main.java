//import libraries
import java.io.*;
import java.util.Scanner;

public class Main {
    static Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
    static Swing gui = new Swing();
    static Scanner input = new Scanner(System.in);

    //Save data Method
    public static void saveInfo() throws IOException {

        //Saving Process
        FileOutputStream fout = new FileOutputStream("F1ChampData.txt");
        ObjectOutputStream obj = new ObjectOutputStream(fout);

        obj.writeObject(championshipManager);
        obj.flush();
        fout.close();
        obj.close();

        System.out.println("\nData Saved Successfully..");
    }

    //main Method
    public static void main(String[] args) throws IOException {

        //Load Data from File
        try {
            FileInputStream fin = new FileInputStream("F1ChampData.txt");
            ObjectInputStream obj = new ObjectInputStream(fin);

            championshipManager = (Formula1ChampionshipManager) obj.readObject();

            fin.close();
            obj.close();
            System.out.println("\nSuccessfully loaded..");

        }
        catch (ClassNotFoundException | FileNotFoundException notfound) {
            System.out.println("\nFile not found..");
        }

        //menu Process
        while (true){
            championshipManager.mainMenu();

            String selection = "";
            System.out.print("\nEnter your option : ");
            selection = input.next();
            selection = selection.toUpperCase();
            if(selection.equals("X")){
                System.out.println("\n...Thank You, See You Soon!...");
                System.out.println("\n---------------------------------------------------------");
                break;
            }
            else {
                switch (selection) {
                    case "A":
                        System.out.println("Create a New Driver");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.createDriver();
                        break;
                    case "B":
                        System.out.println("Delete a Driver");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.deleteDriver();
                        break;
                    case "C":
                        System.out.println("Change the Driver");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.changeDriver();
                        break;
                    case "D":
                        System.out.println("Add Statistics");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.addStatistics();
                        break;
                    case "E":
                        System.out.println("Display Statistics");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.displayStatistics();
                        break;
                    case "F":
                        System.out.println("Display the Formula 1 Driver Table");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.displayDriverTable();
                        break;
                    case "G":
                        System.out.println("Add a Race Data");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.addRace();
                        break;
                    case "H":
                        System.out.println("Add a Race Data");
                        System.out.println("---------------------------------------------------------");
                        championshipManager.displayChampionshipTable();
                        break;
                    case "I":
                        System.out.println("Save all the Information");
                        System.out.println("---------------------------------------------------------");
                        saveInfo();
                        break;
                    case "J":
                        System.out.println("View Graphical User Interface");
                        System.out.println("---------------------------------------------------------");
                        gui.guiHomePage();
                        break;
                    default:
                        System.out.println("Invalid Input..");
                }
            }
        }
    }
}
