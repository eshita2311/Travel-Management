package travel.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BookingSummary extends JFrame {

    JTable table;
    DefaultTableModel model;

    public BookingSummary() {

        setTitle("📋 Booking Summary");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("📋 Booking Summary", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(heading, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[] { "Type", "Details", "Price" }, 0);

        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBookings();

        setVisible(true);
    }

    private void loadBookings() {

        try {
            Conn c = new Conn();

            // -------- FLIGHT BOOKINGS ----------
            ResultSet rs1 = c.con.createStatement().executeQuery(
                    "SELECT from_city, to_city, journey_date, seats, price FROM flight_booking");

            while (rs1.next()) {
                String details = rs1.getString("from_city") + " → "
                        + rs1.getString("to_city")
                        + " | Date: " + rs1.getString("journey_date")
                        + " | Seats: " + rs1.getInt("seats");

                model.addRow(new Object[] {
                        "Flight",
                        details,
                        "₹" + rs1.getInt("price")
                });
            }

            // -------- HOTEL BOOKINGS ----------
            ResultSet rs2 = c.con.createStatement().executeQuery(
                    "SELECT hotel_name, city, days, persons, price FROM hotel_booking");

            while (rs2.next()) {
                String details = rs2.getString("hotel_name")
                        + ", " + rs2.getString("city")
                        + " | Days: " + rs2.getInt("days")
                        + " | Persons: " + rs2.getInt("persons");

                model.addRow(new Object[] {
                        "Hotel",
                        details,
                        "₹" + rs2.getInt("price")
                });
            }

            // -------- TRANSPORT BOOKINGS ----------
            ResultSet rs3 = c.con.createStatement().executeQuery(
                    "SELECT transport_type, from_city, to_city, distance, price FROM transport_booking");

            while (rs3.next()) {
                String details = rs3.getString("transport_type")
                        + " | " + rs3.getString("from_city")
                        + " → " + rs3.getString("to_city")
                        + " | Distance: " + rs3.getInt("distance") + " km";

                model.addRow(new Object[] {
                        "Transport",
                        details,
                        "₹" + rs3.getInt("price")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading booking summary",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
