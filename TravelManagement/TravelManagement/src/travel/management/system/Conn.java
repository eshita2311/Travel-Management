package travel.management.system;

import java.sql.*;

public class Conn {

    public Connection con;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_db",
                    "root",
                    "230811eshk" // 🔴 replace with your MySQL password
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
