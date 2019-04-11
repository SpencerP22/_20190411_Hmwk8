import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String user ="act311";
        String server = "noynaert.cs.missouriwestern.edu";
        String database = "misc";
        String password = getPassword();
        String state = getState();

        ArrayList<University> uniList = new ArrayList<>();

        getUniversities(user, password, server, database, state, uniList);
        Collections.sort(uniList);
        print(uniList);

        System.out.println("Done");

    }

    private static void getUniversities(String user, String password, String server, String database, String state, ArrayList<University> list) {
        //server connection
        String serverConnection = String.format("jdbc:mysql://%s/%s?user=%s&password=%s", server, database, user, password);
        System.out.println("The server string is " + serverConnection);

        try {
            //creates connection
            Connection conn = DriverManager.getConnection(serverConnection);
            System.out.println("Successful Connection");
            //SELECT statement
            String sqlString = String.format("SELECT instnm, city, webaddr FROM post WHERE stabbr LIKE '%s'", state);
            //creates query statement
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlString);

            while(rs.next()) {
                String name = rs.getString("instnm");
                String city = rs.getString("city");
                String webAddress = rs.getString("webaddr");
                //creates new university object and stores name, city, and web address, then adds to arraylist
                University uni = new University(name, city, webAddress);
                list.add(uni);
                //System.out.printf("%s, %s, %s\n", name, city, webAddress);

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
            System.exit(1);
        }

    }
    //prints array list
    public static void print(ArrayList<University> list) {
        System.out.printf("------- University count: %d -------\n", list.size());
        for(University uni : list) {
            System.out.println(uni);
        }
    }
    //gets state abbreviation
    private static String getState() {
        System.out.print("Enter the two letter state abbreviation: ");
        Scanner input = new Scanner(System.in);
        String state = input.next();
        return state;
    }
    //gets password
    private static String getPassword() {
        System.out.print("Enter the password: ");
        Scanner input = new Scanner(System.in);
        String pass = input.next();
        return pass;
    }
}
