package com.app.repo;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Database_Connection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/train_ticket_reservation";
        System.out.print("Enter your user name of the your database: ");
        String dbName = sc.nextLine();
        System.out.print("Enter your password of the your database: ");
        String dbPass = sc.nextLine();
        String insertQuery = "insert into reservation_info (uname, uemail, umobile, upnr, journey_date, journey_from, journey_to) values(?,?,?,?,?,?,?)";
        String selectQuery = "SELECT * FROM reservation_info WHERE upnr = ?";
        String deleteQuery = "DELETE FROM reservation_info WHERE upnr = ?";
        String updateQuery = "UPDATE reservation_info SET uname = ?, uemail = ?, umobile = ?, journey_date = ?, journey_from = ?, journey_to = ? WHERE upnr = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbName, dbPass);
            System.out.println("Connection established successfully.");

            System.out.println("1 : Book Ticket");
            System.out.println("2 : Show information about the ticket");
            System.out.println("3 : Update the details");
            System.out.println("4 : Delete the ticket");
            System.out.println("5 : Quit");

            for (int i = 0; i < 5; i++) {
                System.out.print("Please Enter the choice: ");
                int choice = sc.nextInt();
                sc.nextLine();



                switch (choice) {
                    case 1:
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        Random random = new Random();
                        int min = 100000;
                        int max = 1000000;
                        int pnr = random.nextInt(max - min) + min;

                        System.out.print("Enter your full name: ");
                        String uname = sc.nextLine();
                        System.out.print("Enter your email: ");
                        String uemail = sc.nextLine();
                        System.out.print("Enter your mobile number: ");
                        String umobile = sc.nextLine();
                        System.out.print("Enter your journey date: ");
                        String journey_date = sc.nextLine();
                        System.out.print("Enter your journey start location: ");
                        String journey_from = sc.nextLine();
                        System.out.print("Enter your journey end location: ");
                        String journey_to = sc.nextLine();
                        System.out.println("Your pnr is : " + pnr);
                        preparedStatement.setString(1, uname);
                        preparedStatement.setString(2, uemail);
                        preparedStatement.setString(3, umobile);
                        preparedStatement.setInt(4, pnr);
                        preparedStatement.setString(5, journey_date);
                        preparedStatement.setString(6, journey_from);
                        preparedStatement.setString(7, journey_to);
                        preparedStatement.execute();
                        break;

                    case 2:
                        System.out.print("Enter your PNR number: ");
                        int searchPnr = sc.nextInt();
                        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                        selectStatement.setInt(1, searchPnr);
                        ResultSet resultSet = selectStatement.executeQuery();
                        if (resultSet.next()) {
                            System.out.println("Ticket Information:");
                            System.out.println("Full Name: " + resultSet.getString("uname"));
                            System.out.println("Email: " + resultSet.getString("uemail"));
                            System.out.println("Mobile Number: " + resultSet.getString("umobile"));
                            System.out.println("Journey Date: " + resultSet.getString("journey_date"));
                            System.out.println("Journey From: " + resultSet.getString("journey_from"));
                            System.out.println("Journey To: " + resultSet.getString("journey_to"));
                        } else {
                            System.out.println("No ticket found with PNR: " + searchPnr);
                        }
                        break;


                    case 3:
                        System.out.print("Enter your PNR number: ");
                        int updatePnr = sc.nextInt();
                        sc.nextLine();  // Consume the leftover newline character

                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

                        System.out.print("Enter your new full name: ");
                        String newUname = sc.nextLine();
                        System.out.print("Enter your new email: ");
                        String newUemail = sc.nextLine();
                        System.out.print("Enter your new mobile number: ");
                        String newUmobile = sc.nextLine();
                        System.out.print("Enter your new journey date: ");
                        String newJourneyDate = sc.nextLine();
                        System.out.print("Enter your new journey start location: ");
                        String newJourneyFrom = sc.nextLine();
                        System.out.print("Enter your new journey end location: ");
                        String newJourneyTo = sc.nextLine();

                        updateStatement.setString(1, newUname);
                        updateStatement.setString(2, newUemail);
                        updateStatement.setString(3, newUmobile);
                        updateStatement.setString(4, newJourneyDate);
                        updateStatement.setString(5, newJourneyFrom);
                        updateStatement.setString(6, newJourneyTo);
                        updateStatement.setInt(7, updatePnr);

                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Ticket updated successfully.");
                        } else {
                            System.out.println("No ticket found with PNR: " + updatePnr);
                        }
                        break;



                    case 4:

                        System.out.print("Enter you pnr : ");
                        int deletePnr = sc.nextInt();
                        PreparedStatement pst = connection.prepareStatement(deleteQuery);
                        pst.setInt(1,deletePnr);
                        int count = pst.executeUpdate();
                        if (count > 0){
                            System.out.println("Ticket is deleted");
                        }  else {
                            System.out.println("No ticket found with PNR: " + deletePnr);
                        }
                        break;

                    case 5:
                        System.out.println("You are exit successfully");
                        i = 10;
                        break;
                    default:
                        System.out.println("Your choice is not matching!!!......");
                        System.out.println("Please enter the valid choice");
                }
            }
            connection.close();
            sc.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
