import java.sql.*;

public class Passenger {


    private int PassengerId;
    private String address;
    private String name;

    public Passenger(String name, String address) {
        this.name = name;
        this.address = address;
        PassengerId++;
    }

    public static void addPassenger(Passenger p) throws SQLException {
        int m = -1;
        PreparedStatement PrepareAstatment = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");
        PrepareAstatment = con.prepareStatement("INSERT INTO PASSENGER (ADDRESS,NAME) VALUES(?,?)");
        PrepareAstatment.setString(1, p.getAddress());
        PrepareAstatment.setString(2, p.getName());
        m = PrepareAstatment.executeUpdate();
    }

    public static void ViewAll() throws SQLException {
        int count = 0;
        System.out.printf("%11s | %-10s | %-10s |\n", "PassengerID", "NAME", "Address");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * FROM PASSENGER");
            while (rs.next()) {
                int passengerid = rs.getInt("idpassenger");
                String name = rs.getString("name");
                String address = rs.getString("Address");
                System.out.printf("%1d          | %-10s | %-10s |\n\n", passengerid, name, address);
                count++;
            }
            if (count == 0) {
                System.out.println("THERE IS NO PASSENGER AT THE MOMENT");
            }
    }


    public static void Remove(int index) throws SQLException {

        int m = -1;
        PreparedStatement PrepareAstatment = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline", "root", "come4slash");

        PrepareAstatment = con.prepareStatement("DELETE FROM  PASSENGER WHERE idpassenger=?");

        PrepareAstatment.setInt(1, index);


        m = PrepareAstatment.executeUpdate();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                ", passengerId=" + PassengerId +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
