/*

this project about an air line system
Done by: Abdelrahman mohamed abdelmonem cs2

containing 12 function

for assigning passenger , flights descritption and schadule flights

using mysql database jdbc through jdbc connector api

containing 3 tables

extra thing is the color class its just for the decoration of the terminal window

hope u like it <3

*/
import java.util.Scanner;

public class Main {
    static Color cc = new Color();

    public static void main(String[] args) {

        header();
        MainMenu();

    }

    public static void header() {
        System.out.println(cc.BLACK_BOLD + cc.CYAN_COLOR +cc.GREEN_BACKGROUND_BRIGHT+ "--------->Airline Reservation System<----------" + cc.RESET);

    }

    public static void MainMenu() {

        System.out.println(cc.CYAN_COLOR+"1-passenger view"+cc.RESET);

        System.out.println(cc.CYAN_COLOR+"2-Flight Management view"+cc.RESET);

        System.out.println(cc.CYAN_COLOR+"3-Exit system"+cc.RESET);
        try {
            int choice;

            Scanner in = new Scanner(System.in);
            System.out.println(cc.BLACK+"Choice: "+ cc.RESET);
            choice = in.nextShort();

            switch (choice) {
                case 1 -> {
                    System.out.println();
                    passenger();
                }
                case 2 -> {
                    System.out.println();
                    FlightMenu();
                }
                case 3 -> ExitMassage();

                case 4 -> MainMenu();

                default -> throw new Exception("Not Valid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            MainMenu();
        }

    }

    public static void passenger() {
        System.out.println(cc.YELLOW_BACKGROUND+"---->  Passenger Menu  <----"+cc.RESET);
        System.out.println(cc.RED+"1-Insert Passenger"+cc.RESET);
        System.out.println(cc.RED+"2-View All Passengers"+cc.RESET);
        System.out.println(cc.RED+"3-Remove Passenger"+cc.RESET);
        System.out.println(cc.RED+"4-back to Main menu"+cc.RESET);
        try {
            int choice;
            Scanner scan = new Scanner(System.in);
            System.out.println(cc.BLACK+"Choice: "+cc.RESET);

            choice = scan.nextShort();
            scan.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("---->  NEW PASSENGER  <----");
                    System.out.println("Full Name: ");
                    String name = scan.nextLine();
                    System.out.println("Address: ");
                    String address = scan.nextLine();
                    Passenger.addPassenger(new Passenger(name, address));
                    System.out.println("New Passenger Added successfully");
                    passenger();
                }
                case 2 -> {
                    Passenger.ViewAll();
                    passenger();
                }
                case 3 -> {
                    System.out.println("Enter the id of the Passenger You  Want To Remove");
                    Passenger.ViewAll();
                    int index = scan.nextInt();
                    Passenger.Remove(index);
                    System.out.println("Passenger Removed");
                    passenger();
                }
                case 4 -> MainMenu();
                default -> throw new Exception("PLEASE ENTER THE RIGHT NUMBER");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            passenger();
        }

    }

    public static void FlightMenu()  {
        System.out.println(cc.PURPLE_BACKGROUND+"---->  Flight Management Menu  <----"+cc.RESET);
        System.out.println(cc.GREEN+"1-Add New Flight Description        "+cc.RESET);
        System.out.println(cc.GREEN+"2-View All Flight Description       "+cc.RESET);
        System.out.println(cc.GREEN+"3-Remove Flight Description         "+cc.RESET);
        System.out.println(cc.GREEN+"4-Schedule New Flight               "+cc.RESET);
        System.out.println(cc.GREEN+"5-View All Scheduled Flights        "+cc.RESET);
        System.out.println(cc.GREEN+"6-Cancel Scheduled Flight           "+cc.RESET);
        System.out.println(cc.GREEN+"7-Main Menu"+cc.RESET);
        try {
            int choice;
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            System.out.println(cc.BLACK+"Choice: "+cc.RESET);
            choice = scan.nextShort();
            scan.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("---->  NEW FLIGHT DESCRIPTION  <----");
                    System.out.println("To:           ");
                    String to = scan.nextLine();
                    System.out.println("From:         ");
                    String from = scan.nextLine();
                    FlightDescription.Add(new FlightDescription(to, from));
                    System.out.println("Flight Description Added Successfully ");
                    FlightMenu();

                }
                case 2 -> {
                    FlightDescription.ViewAll();
                    System.out.println("\n");
                    FlightMenu();
                }

                //some function
                case 3 -> {
                    System.out.println("Enter the ID of the Flight You  Want To Remove");
                    FlightDescription.ViewAll();
                    int index = scan.nextInt();
                    FlightDescription.Delete(index - 1);
                    System.out.println("Flight Removed");
                    FlightMenu();
                }
                //some function
                case 4 -> {
                    System.out.println(" ----> SCHEDULE New FLIGHT <----");
                    FlightDescription.ViewAll();
                    System.out.println("PLEASE ENTER THE FLIGHT TO SCHEDULE");
                    int indexFD = scan.nextInt();
                    System.out.println("DepartureTime: ");
                    String DepartTime = scan2.nextLine();
                    System.out.println("ArrivalTime:  ");
                    String arrivalTime = scan2.nextLine();
                    System.out.println("DATE   (YYYY/MM/DD)   ");
                    String Date = scan2.nextLine();
                    System.out.println("Capacity:     ");
                    int capacity = scan2.nextInt();
                          SchaduleFlights.addSchaduleFlights(new SchaduleFlights(DepartTime, arrivalTime, Date, capacity),indexFD);
                            FlightMenu();
                        }

                case 5 -> {
                    SchaduleFlights.ViewAll();
                    FlightMenu();
                }

                case 6 -> {
                    SchaduleFlights.ViewAll();
                    System.out.println("ENTER THE FLIGHT NUMBER TO DELETE");
                    int indexSF = scan2.nextInt();
                    SchaduleFlights.RemoveSchanduleFlight(indexSF);
                    FlightMenu();
                }
                //some function
                case 7 -> MainMenu();
                default -> throw new Exception("PLEASE ENTER THE RIGHT NUMBER");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            FlightMenu();
        }
    }

    public static void ExitMassage() {
        System.out.println("--------->THANK YOU FOR USING OUR AIRLINES<---------");
    }

}



