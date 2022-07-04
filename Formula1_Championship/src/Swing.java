//import libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Swing {

    //Create variables
    static String pointsOrder = "descending";
    static String firstPositionOrder = "descending";
    static String dateOrder = "descending";
    static String race = "oldRace";

    //Homepage Interface Method
    public void guiHomePage() {
        //Create the JFrame, JPanel, ImageIcons, JLabels and JButtons
        JFrame frame = new JFrame("Formula 1 Championship");
        JPanel panel = new JPanel();
        ImageIcon icon1 = new ImageIcon("logo2.png");
        ImageIcon icon2 = new ImageIcon("logo1.png");
        JLabel label1 = new JLabel("WELCOME");
        JLabel label2 = new JLabel("F O R M U L A - 1 - C H A M P I O N S H I P");
        JLabel label3 = new JLabel(icon2);
        JButton button1 = new JButton("Driver Details");
        JButton button2 = new JButton("Race");
        JButton button3 = new JButton("Race Details");
        Font font1 = new Font("Calibri", Font.BOLD, 40);
        Font font2 = new Font("Calibri", Font.PLAIN, 15);

        //Set the location and size of the labels and buttons
        label1.setBounds(246, 50, 182, 43);
        label2.setBounds(208, 100, 250, 22);
        label3.setSize(675, 450);
        button1.setBounds(124, 280, 120, 35);
        button2.setBounds(274, 280, 120, 35);
        button3.setBounds(424, 280, 120, 35);

        frame.setSize(675, 450);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(icon1.getImage());
        panel.setSize(675,450);
        panel.setLayout(null);
        panel.setVisible(true);
        label1.setFont(font1);
        label2.setFont(font2);
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);

        //Adding elements
        frame.add(panel);
        panel.add(label3);
        label3.add(label1);
        label3.add(label2);
        label3.add(button1);
        label3.add(button2);
        label3.add(button3);

        //Button Action
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiDriverTable(Main.championshipManager);
                frame.setVisible(false);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiRandomRace(Main.championshipManager);
                frame.setVisible(false);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiRaceTable(Main.championshipManager);
                frame.setVisible(false);
            }
        });


    }

    //Driver Table Interface Method
    public void guiDriverTable(Formula1ChampionshipManager championshipManager) {
        //Create the JFrame, JPanel, ImageIcons, JLabels and JButtons
        JFrame frame = new JFrame("Formula 1 Championship Driver Table");
        JPanel panel = new JPanel();
        ImageIcon icon1 = new ImageIcon("logo2.png");
        JLabel label1 = new JLabel("F O R M U L A - 1 - C H A M P I O N S H I P   D R I V E R   T A B L E",SwingConstants.CENTER);
        JLabel label2 = new JLabel("Sort By : ");
        JButton button1 = new JButton("Position");
        JButton button2 = new JButton("Points");
        JButton button3 = new JButton("Home");

        //Set the location and size of the labels and buttons
        label1.setBounds(50,0,600,30);
        label2.setBounds(185, 380, 100, 25);
        button1.setBounds(260, 380, 100, 25);
        button2.setBounds(390, 380, 100, 25);
        button3.setBounds(550, 425, 100, 25);

        frame.setSize(700,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(icon1.getImage());
        panel.setSize(700, 500);
        panel.setLayout(null);
        panel.setVisible(true);

        //Adding elements
        frame.add(panel);
        panel.add(label1);
        panel.add(label2);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        //Button Action
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstPositionOrder.equals("ascending")){
                    firstPositionOrder = "descending";
                }
                else {
                    firstPositionOrder = "ascending";
                }
                Object[][] data = driverTableData(championshipManager, firstPositionOrder, pointsOrder, "positions");
                showDriverTable(panel, data);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pointsOrder.equals("ascending")){
                    pointsOrder = "descending";
                } else {
                    pointsOrder = "ascending";
                }
                Object[][] data = driverTableData(championshipManager, firstPositionOrder, pointsOrder, "points");
                showDriverTable(panel, data);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiHomePage();
                frame.setVisible(false);
            }
        });

        //Display Table
        String column[] = {"Driver Name", "Team Name", "1st Positions", "2nd Positions", "3rd Positions", "R. Completed", "Total Points"};
        Object[][] data = driverTableData(championshipManager, "descending", "descending", "points");

        JTable table = new JTable(data,column);
        table.setBounds(42,28,600,300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 28, 600, 300);

        panel.add(scrollPane);


    }

    //Driver Table Data Method
    public Object[][] driverTableData(Formula1ChampionshipManager championshipManager, String sortingPositionsMethod, String sortingPointsMethod, String sortingTitle){
        if(sortingPositionsMethod.equals("descending") && sortingTitle.equals("positions")){
            Collections.sort(championshipManager.drivers, new PositionAscending());
        }
        else if(sortingPositionsMethod.equals("ascending") && sortingTitle.equals("positions")){
            Collections.sort(championshipManager.drivers, new PositionDescending());
        }
        else if(sortingPointsMethod.equals("ascending") && sortingTitle.equals("points")) {
            Collections.sort(championshipManager.drivers, new PointsAscending());
        }
        else if(sortingPointsMethod.equals("descending") && sortingTitle.equals("points")) {
            Collections.sort(championshipManager.drivers, new PointsDescending());
        }
        Object[][] data = new Object[championshipManager.drivers.size()][7];

        for (int i = 0; i < championshipManager.drivers.size(); i++) {
            data[i][0] = championshipManager.drivers.get(i).getName();
            data[i][1] = championshipManager.drivers.get(i).getTeam();
            data[i][2] = championshipManager.drivers.get(i).firstPositions;
            data[i][3] = championshipManager.drivers.get(i).secondPositions;
            data[i][4] = championshipManager.drivers.get(i).thirdPositions;
            data[i][5] = championshipManager.drivers.get(i).numOfRaces;
            data[i][6] = championshipManager.drivers.get(i).numOfPoints;
        }
        return data;
    }

    //Show Driver Table Method
    public void showDriverTable(JPanel panel, Object[][] data){
        String column[] = {"Driver Name", "Team Name", "1st Positions", "2nd Positions", "3rd Positions", "R. Completed", "Total Points"};

        JTable table = new JTable(data,column);
        table.setBounds(42,28,600,300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 28, 600, 300);

        panel.add(scrollPane);
    }

    //Random Race Interface Method
    public void guiRandomRace(Formula1ChampionshipManager championshipManager){
        //Create the JFrame, JPanel, ImageIcons, JLabels and JButtons
        JFrame frame = new JFrame("Formula 1 Championship Race");
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("F O R M U L A - 1 - C H A M P I O N S H I P   R A C E",SwingConstants.CENTER);
        JLabel label2 = new JLabel("Race : ");
        JButton button1 = new JButton("START");
        JButton button2 = new JButton("Home");
        ImageIcon icon1 = new ImageIcon("logo2.png");

        //Set the location and size of the labels and buttons
        label1.setBounds(40,0,600,30);
        label2.setBounds(255, 380, 100, 25);
        button1.setBounds(325, 380, 100, 25);
        button2.setBounds(550, 425, 100, 25);

        frame.setSize(700,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(icon1.getImage());
        panel.setSize(700, 500);
        panel.setLayout(null);
        panel.setVisible(true);

        //Adding elements
        frame.add(panel);
        panel.add(label1);
        panel.add(label2);
        panel.add(button1);
        panel.add(button2);

        //Button Action
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(race.equals("newRace")){
                    race = "oldRace";

                }
                else {
                    race = "newRace";
                }

                Object[][] data = randomRaceData(championshipManager, "newRace");
                showRandomRace(panel, data);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiHomePage();
                frame.setVisible(false);
            }
        });

        //Display Random Race Table
        String column[] = {"Driver Name", "Position"};
        Object[][] data = randomRaceData(championshipManager, "newRace");

        JTable table = new JTable(data,column);
        table.setBounds(42,28,600,300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 28, 600, 300);

        panel.add(scrollPane);
    }

    //Random Position Method
    public ArrayList<Integer> randomPositionsGen (Formula1ChampionshipManager championshipManager){
        int count = championshipManager.drivers.size();
        ArrayList<Integer> randomPositions = new ArrayList();
        Random random = new Random();
        for(int i = 0; i < count; i++){
            int randNum = random.nextInt(count + 1);
            while (randNum == 0 || randomPositions.contains(randNum)){
                randNum = random.nextInt(count + 1);
            }
            randomPositions.add(randNum);
        }
        return randomPositions;
    }

    //Random Race Data Method
    public Object[][] randomRaceData (Formula1ChampionshipManager championshipManager, String race) {
        if(race.equals("newRace")){
            randomPositionsGen(championshipManager);
        }
        else {
            randomPositionsGen(championshipManager);
        }

        Object[][] data = new Object[randomPositionsGen(championshipManager).size()][2];

        for (int i = 0; i < randomPositionsGen(championshipManager).size(); i++) {
            data[i][0] = championshipManager.drivers.get(i).getName();
            data[i][1] = randomPositionsGen(championshipManager).get(i);
        }
        return data;
    }

    //Display Random Race Table Method
    public void showRandomRace(JPanel panel, Object[][] data){
        String column[] = {"Driver Name", "Position"};
        JTable table = new JTable(data,column);
        table.setBounds(42,28,600,300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 28, 600, 300);

        panel.add(scrollPane);
    }

    //Race Table Interface Method
    public void guiRaceTable(Formula1ChampionshipManager championshipManager){
        //Create the JFrame, JPanel, ImageIcons, JLabels and JButtons
        JFrame frame = new JFrame("Formula 1 Championship Table");
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("F O R M U L A - 1 - C H A M P I O N S H I P   T A B L E",SwingConstants.CENTER);
        JLabel label2 = new JLabel("Sort By : ");
        JButton button1 = new JButton("Date");
        JButton button2 = new JButton("Home");
        ImageIcon icon1 = new ImageIcon("logo2.png");

        //Set the location and size of the labels and buttons
        label1.setBounds(40,0,600,30);
        label2.setBounds(255, 380, 100, 25);
        button1.setBounds(325, 380, 100, 25);
        button2.setBounds(550, 425, 100, 25);

        frame.setSize(700,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(icon1.getImage());
        panel.setSize(700, 500);
        panel.setLayout(null);
        panel.setVisible(true);

        //Adding elements
        frame.add(panel);
        panel.add(label1);
        panel.add(label2);
        panel.add(button1);
        panel.add(button2);

        //Button Action
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateOrder.equals("ascending")){
                    dateOrder = "descending";
                }
                else {
                    dateOrder = "ascending";
                }
                Object[][] data = raceTableData(championshipManager, dateOrder);
                showRaceTable(panel, data);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiHomePage();
                frame.setVisible(false);
            }
        });

        //Display Table
        String column[] = {"Date", "Position", "Driver Name"};
        Object[][] data = raceTableData(championshipManager, "descending");

        JTable table = new JTable(data,column);
        table.setBounds(42,28,600,300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 28, 600, 300);

        panel.add(scrollPane);
    }

    //Race Table Data Method
    public Object[][] raceTableData (Formula1ChampionshipManager championshipManager, String sortingDateMethod) {
//        if (sortingDateMethod.equals("ascending")) {
//            Collections.sort(championshipManager.drivers, new PositionAscending());
//        } else if (sortingDateMethod.equals("descending")) {
//            Collections.sort(championshipManager.drivers, new PositionDescending());
//        }

        Object[][] data = new Object[championshipManager.drivers.size()][3];

        for (int i = 0; i < championshipManager.raceData.size(); i++) {
            data[i][0] = championshipManager.raceData.get(i).getDate();
            for (int j = 0; j < 10; j++) {
                data[j][1] = (j + 1);
                data[j][2] = championshipManager.raceData.get(i).getPositionName().get(j);
            }
        }
        return data;
    }

    //Display Race Table Method
    public void showRaceTable(JPanel panel, Object[][] data){
        String column[] = {"Date", "Position", "Driver Name"};
        JTable table = new JTable(data,column);
        table.setBounds(42,28,600,300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 28, 600, 300);

        panel.add(scrollPane);
    }
}
