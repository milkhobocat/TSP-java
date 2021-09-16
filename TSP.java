import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/*cs211 final project 2021
20 Xandra Uy 
18376906
*/
public class TSP {

    /*
     * JButton button; public static void main(String args[]) { new TSP();
     */

    // main method
    public static void main(String args[]) {

        designMethod();

    }

    // ------------design method-----------------------------
    public static void designMethod() {

        // // ----------label stuff---------------
        JLabel label = new JLabel(); // create label
        label.setText("Enter Order:                         "); // this text
        label.setForeground(Color.WHITE);// colour of the text
        label.setFont(new Font("MV Boli", Font.BOLD, 20));// font properties
        label.setBounds(80, 100, 250, 250);// text location

        // -------------------text field----------------
        // JTextField orderHere = new JTextField();
        // orderHere.setBounds(80, 340, 810, 30);
        // orderHere.setFont(new Font("MV Boli", Font.PLAIN, 15));
        // orderHere.setBackground(new Color(252, 238, 212));
        // orderHere.setBorder(javax.swing.BorderFactory.createEmptyBorder());// get rid
        // of highlight border

        // ----text area---------
        // im changing from textfield to jtextarea as i have discovered textfields only
        // allow one
        // line of text whereas jtextareas allows recognise new lines being made

        JTextArea typeHere = new JTextArea();
        // typeHere.setText("Type Here... ");
        typeHere.setBounds(550, 250, 500, 350);
        typeHere.setLineWrap(true);
        typeHere.setBackground(new Color(28, 25, 51));
        // typeHere.setBorder(BorderFactory.createBevelBorder(1));
        typeHere.setForeground(Color.WHITE);

        typeHere.setWrapStyleWord(true);
        typeHere.setFont(new Font("MV Boli", Font.PLAIN, 15));

        // scroll bar
        JScrollPane moveYo = new JScrollPane(typeHere);
        moveYo.setPreferredSize(new Dimension(500, 350));
        moveYo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // ----------button----------
        JButton button = new JButton();
        // button.setBounds(40, 100, 150, 30);
        // // button.addActionListener(e -> System.out.println("test")); //just to test
        // if
        // // button works
        button.setText("Compute");// button label
        // button.setFocusable(false); // to get rid of the highlight around the button
        button.setOpaque(true);// so that the button can be coloured
        button.setBorderPainted(false); // so that the border isnt the only thing
        // coloured
        button.setFont(new Font("MV Boli", Font.BOLD, 15));// font properties for
        // button
        button.setForeground(Color.WHITE); // colour of the text
        button.setBackground(new Color(52, 51, 76)); // colour of button

        // -------------main frame stuff-------------
        JFrame projectWindow = new JFrame();// create a frame
        projectWindow.setTitle("Pizza (^_^)!");// title of frame
        projectWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to be able to exit out of applicaiton
        projectWindow.setResizable(false); // wont be able to resize window
        projectWindow.setSize(660, 470);// size of frame
        projectWindow.getContentPane().setBackground(new Color(123, 127, 167)); //
        projectWindow.setLayout(new FlowLayout());// size of frame
        // projectWindow.setLocationRelativet;// size of frame

        // add labels
        projectWindow.add(label); // to show up label in frame
        // add button
        projectWindow.add(button); // to show up button in frame
        // add text field
        // projectWindow.add(orderHere); // to show up text field in frame
        // add jtextarea
        // projectWindow.add(typeHere); // to show jtext area to frame
        // add scroll pane
        projectWindow.add(moveYo);// to show scroll bar to frame
        projectWindow.setVisible(true);// make frame visible
        // colour of frame

        // ----------------button actionListener---------------
        // using lambda expression once this button is clicked a new window opens
        button.addActionListener(e -> {

            String data = typeHere.getText();
            // System.out.println(data);
            projectWindow.dispose(); // this will get rid of previous window
            // // second window opens
            JFrame projectWindow2 = new JFrame();
            projectWindow2.setTitle("Pizza (^_^)!");// title of frame
            projectWindow2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to be able
            // to exit out of applicaiton
            projectWindow2.setResizable(false); // wont be able to resize window
            projectWindow2.setSize(660, 750);
            projectWindow2.setLayout(new FlowLayout());

            projectWindow2.getContentPane().setBackground(new Color(123, 127, 167)); //

            // colour of frame
            // // pass user input into this method

            JTextArea displayData = new JTextArea();
            displayData.setText(userInputMethod(data));
            displayData.setBounds(550, 250, 500, 300);
            displayData.setLineWrap(true);
            displayData.setBackground(new Color(28, 25, 51));
            displayData.setBorder(BorderFactory.createBevelBorder(1));
            displayData.setForeground(Color.WHITE);
            displayData.setEditable(false); // so data can not be edited, can only see what is shown

            displayData.setWrapStyleWord(true);
            displayData.setFont(new Font("MV Boli", Font.PLAIN, 15));

            // scroll bar
            JScrollPane seeYo = new JScrollPane(displayData);
            seeYo.setPreferredSize(new Dimension(500, 300));
            seeYo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            // projectWindow2.add(displayData);
            projectWindow2.add(seeYo);
            projectWindow2.setVisible(true);// make frame visible

            // userInputMethod(data);

            /*
             * 
             * 99,maynooth,20,53,6 99,maynooth,20,465,6
             * 
             */

        });

    }
    ////////// -----------------------------------the beginning of Floyd warshall
    ////////// algorithm
    ////////// --------------------

    // gainin g user input from the jtextarea, pass it as a string and split every
    // data onto an array

    public static String userInputMethod(String data) {

        System.out.println(data);
        String[] orderInput = data.split("\n"); // split to new row after a new
        // line

        /*
         * for(int i = 0; i < orderInput.length; i++) {
         * System.out.println(orderInput[i]); }
         */

        String[][] sections = new String[orderInput.length][5]; // declaring my 2d array for my
        // data

        // System.out.println(orderInput);

        // /////////////////////////////////////////////////////////////////////////////
        // // populating the array
        int start = 0;
        int pos = 0;
        for (int i = 0; i < orderInput.length; i++) {// row goes up to 99
            String order = orderInput[i];
            System.out.println(order);

            for (int a = 0; a < order.length(); a++) {
                // System.out.println("here");
                if (order.charAt(a) == ',') {
                    // System.out.println("here1");
                    sections[i][pos] = order.substring(start, a);

                    System.out.println("section: " + sections[i][pos]);
                    pos++;
                    start = a + 1;
                }
                // debugging
                // System.out.println("here1");

            }

            sections[i][pos] = order.substring(start);

            pos = 0;
            start = 0;
        }

        System.out.println("\n\n\n");
        for (int i = 0; i < orderInput.length; i++) {// row goes up to 99
            for (int a = 0; a < 5; a++) {
                System.out.print(sections[i][a] + " --------- ");
            }
            System.out.println();
        }

        // System.out.println(Arrays.deepToString(sections)); // to print out everything
        // in the array

        /*
         * 99,maynooth,20,53,6 99,maynooth,20,465,6
         */

        String output = "";

        for (int i = 0; i < sections.length; i++) {// row goes up to 99

            for (int j = 0; j < 5; j++) {// col goes up to 4

                output += sections[i][j] + " ";
            }

            output += "\n";

        }

        double[][] distanceArray = new double[orderInput.length][orderInput.length];
        distanceArray = calculateGap(distanceArray, sections);

        String fastestWay = "";
        double fastestDistance = 0.0;
        boolean[] hasVisitedHouse = new boolean[orderInput.length];

        getFastestWay(distanceArray, fastestWay, fastestDistance, hasVisitedHouse);

        return output;

    }

    // to calculate which is the fastest route for the delivery man
    public static void getFastestWay(double[][] pointData, String fastestWay, double fastestDistance,
            boolean[] hasVisitedHouse) {

        // System.out.println(Arrays.deepToString(pointData));

        fastestWay = "1"; // tracking from this point...
        int currentHouse = 1;// first house
        int nextHouse = 0;
        fastestDistance = 0.0; // tracking distance

        hasVisitedHouse[0] = true;

        for (int b = 0; b < hasVisitedHouse.length; b++) {
            // find closest house from current house
            double closestHouseDistance = Double.MAX_VALUE;

            for (int i = 0; i < hasVisitedHouse.length; i++) {

                if (hasVisitedHouse[i] == false && pointData[currentHouse][i] < closestHouseDistance
                        && currentHouse != i) {
                    closestHouseDistance = pointData[currentHouse][i];
                    nextHouse = i;
                }
            }

            fastestWay += "," + nextHouse;
            if (closestHouseDistance != Double.MAX_VALUE) {
                fastestDistance += closestHouseDistance;
            }

            hasVisitedHouse[nextHouse] = true; // updates array to say we've been to the house
            closestHouseDistance = Double.MAX_VALUE;
        }

        System.out.println("Path :" + fastestWay);
        System.out.println("Distance :" + fastestDistance);
    }

    // to calculate gap between houses/nodes
    public static double[][] calculateGap(double[][] pointData, String[][] sections) {

        for (int i = 0; i < pointData.length; i++) {
            for (int j = 0; j < pointData.length; j++) {

                double house1X = Double.parseDouble(sections[i][3]);
                double house1Y = Double.parseDouble(sections[i][4]);
                double house2X = Double.parseDouble(sections[j][3]);
                double house2Y = Double.parseDouble(sections[j][4]);

                // System.out.println("1X: " + house1X + "1Y: " + house1Y + "2X: " + house2X +
                // "2Y: " + house2Y);
                // System.out.println("calculated distance: " + haversine(house1X, house1Y,
                // house2X, house2Y));

                pointData[i][j] = broDistance(house1X, house1Y, house2X, house2Y);
            }
        }

        return pointData;
    }

    // find distance two different nodes
    // haversine method
    public static double broDistance(double spoon, double fork, double spork, double knife) {
        double paper = Math.toRadians(spork - spoon);
        double pen = Math.toRadians(knife - fork);

        spoon = Math.toRadians(spoon);
        spork = Math.toRadians(spork);

        double a = Math.sin(paper / 2) * Math.sin(paper / 2)
                + Math.cos(spoon) * Math.cos(spork) * Math.sin(pen / 2) * Math.sin(pen / 2);

        return (2 * 6371 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)) / 100) * 100;

    }
}
