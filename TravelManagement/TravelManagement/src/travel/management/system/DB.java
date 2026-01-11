package travel.management.system;

import java.sql.*;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/travel_db";
    private static final String USER = "root";
    private static final String PASS = "YOUR_MYSQL_PASSWORD";

    public static void saveFlight(String from, String to, String date, int seats, int price) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String q = "INSERT INTO flight_booking VALUES (null, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, from);
            ps.setString(2, to);
            ps.setString(3, date);
            ps.setInt(4, seats);
            ps.setInt(5, price);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveHotel(String hotel, String city, int days, int persons, int price) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String q = "INSERT INTO hotel_booking VALUES (null, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, hotel);
            ps.setString(2, city);
            ps.setInt(3, days);
            ps.setInt(4, persons);
            ps.setInt(5, price);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveTransport(
            String type, String from, String to, int km, int price) {

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            String q = "INSERT INTO transport_booking VALUES (null, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q);

            ps.setString(1, type);
            ps.setString(2, from);
            ps.setString(3, to);
            ps.setInt(4, km);
            ps.setInt(5, price);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
