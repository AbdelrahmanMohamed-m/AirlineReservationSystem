import java.sql.*;

public class SchaduleFlights extends FlightDescription {
    private static int capacity;

    private static String arrivalTime;


    private static String departureTime;

    private static String Date;
public SchaduleFlights(){};
    public SchaduleFlights(String departTime, String arrivalTime, String date, int capacity) {
      departureTime=departTime;
      this.arrivalTime=arrivalTime;
      Date=date;
      this.capacity=capacity;
    }


    public static void addSchaduleFlights(SchaduleFlights SF,int index) throws SQLException {
        int m = -1;
        PreparedStatement PrepareAstatment = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");
        PrepareAstatment = con.prepareStatement("UPDATE sflights SET  DATE=? ,ARRIVALTIME=? ,DEPARTURETIME=? ,CAPACITY=? WHERE IDSFLIGHT=? ");
        PrepareAstatment.setString(1, getDate());
        PrepareAstatment.setString(2, getArrivalTime());
        PrepareAstatment.setString(3, getDepartureTime());
        PrepareAstatment.setInt(4, SF.getCapacity());
        PrepareAstatment.setInt(5,index);

        m = PrepareAstatment.executeUpdate();

    }

    public static void ViewAll() throws SQLException {
        int counter = 0;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * from sflights");

        while (rs.next()) {
            int flightdescriptionid = rs.getInt("IDSFLIGHT");
            String to = rs.getString("TO_COUNTRY");
            String from = rs.getString("FROM_COUNTRYS");
            String Departuretime = rs.getString("departuretime");
            String arrivaltime = rs.getString("ARRIVALTIME");
            String Date = rs.getString("DATE");
            int capacity = rs.getInt("CAPACITY");
            ++counter;
            System.out.printf("%1d             | %5s | %-20s | %-20s | %-10s | %-10s | %-10s |\n",
                    flightdescriptionid, to, from, Departuretime, arrivaltime,Date, capacity);
            if (counter == 0) {

                System.out.println("THERE IS NO PASSENGER AT THE MOMENT");
            }

        }
    }

    public SchaduleFlights(FlightDescription chosenFlightDescription, String departureTime, String arrivalTime, String Date, int capacity) {
        super(chosenFlightDescription.getTo(), chosenFlightDescription.getFrom());
        this.capacity = capacity;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.Date = Date;
    }

    public static void RemoveSchanduleFlight(int index) throws SQLException {
        int m = -1;
        PreparedStatement PrepareAstatment = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");

        PrepareAstatment = con.prepareStatement("DELETE FROM  sflights WHERE idsflight=?");

        PrepareAstatment.setInt(1, index);

        m = PrepareAstatment.executeUpdate();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public static String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public static String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
