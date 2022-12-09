import java.sql.*;

public class FlightDescription {

    private String to;
    private String from;

    public static void Add(FlightDescription flightdata) throws SQLException {
        int m = -1;
        PreparedStatement PrepareAstatment = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");
        PrepareAstatment = con.prepareStatement("INSERT INTO flightdescription (going,from_country) VALUES(?,?)");
        PrepareAstatment.setString(1, flightdata.getTo());
        PrepareAstatment.setString(2, flightdata.getFrom());
        m = PrepareAstatment.executeUpdate();
        PrepareAstatment=con.prepareStatement("INSERT INTO SFLIGHTS(IDSFLIGHT,TO_COUNTRY,FROM_COUNTRYS) SELECT FD_ID,GOING,FROM_COUNTRY from FLIGHTDESCRIPTION");
        m= PrepareAstatment.executeUpdate();
    }

    public static void ViewAll() throws SQLException {
        int count = 0;
        System.out.printf("%8s | %-20s | %-20s  |\n", "FlightID", "FROM", "TO");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("Select * FROM FLIGHTDESCRIPTION");

        while (rs.next()) {
            int flightdescriptionid = rs.getInt("FD_ID");

            String to = rs.getString("going");

            String from = rs.getString("from_country");
            System.out.printf("%1d        | %-20s |%-20s   |\n", flightdescriptionid, to, from);
            count++;
        }

        if (count == 0) {

            System.out.println("THERE IS NO PASSENGER AT THE MOMENT");
        }
    }


    public static void Delete(int index) throws SQLException {
        int m = -1;
        PreparedStatement PrepareAstatment = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");

        PrepareAstatment = con.prepareStatement("DELETE FROM  FLIGHTDESCRIPTION WHERE FD_ID=?");

        PrepareAstatment.setInt(1, index);

        m = PrepareAstatment.executeUpdate();
    }

public FlightDescription(){};
    public FlightDescription(String from, String to) {
        this.to = to;
        this.from = from;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}




