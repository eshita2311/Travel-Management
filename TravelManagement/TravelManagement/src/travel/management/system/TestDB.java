package travel.management.system;

import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        try {
            Conn c = new Conn();

            ResultSet rs = c.con.createStatement().executeQuery("SHOW TABLES");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            System.out.println("DB CONNECTED SUCCESSFULLY");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
