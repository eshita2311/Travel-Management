package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FlightBooking extends JFrame {

    JTextField fromField, toField, dateField;
    JSpinner seatSpinner;
    JLabel totalPriceLabel;

    int pricePerSeat = 5588; // fixed rate
    int totalPrice = 0;

    public FlightBooking() {

        setTitle("✈ Flight Booking");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("✈ Book Your Flight", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(150, 20, 300, 40);
        add(title);

        addLabel("From City", 120);
        fromField = addTextField(120);

        addLabel("To City", 170);
        toField = addTextField(170);

        addLabel("Journey Date", 220);
        dateField = addTextField(220);

        addLabel("Seats", 270);
        seatSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        seatSpinner.setBounds(260, 270, 200, 30);
        add(seatSpinner);

        totalPriceLabel = new JLabel("Total Price: ₹0");
        totalPriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPriceLabel.setBounds(220, 310, 250, 30);
        add(totalPriceLabel);

        seatSpinner.addChangeListener(e -> calculatePrice());

        JButton bookBtn = new JButton("✈ Book Flight");
        bookBtn.setBounds(200, 350, 200, 40);
        bookBtn.setBackground(new Color(0, 123, 255));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(bookBtn);

        bookBtn.addActionListener(e -> bookFlight());

        setVisible(true);
    }

    private void addLabel(String text, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(120, y, 120, 30);
        add(lbl);
    }

    private JTextField addTextField(int y) {
        JTextField tf = new JTextField();
        tf.setBounds(260, y, 200, 30);
        add(tf);
        return tf;
    }

    private void calculatePrice() {
        int seats = (int) seatSpinner.getValue();
        totalPrice = seats * pricePerSeat;
        totalPriceLabel.setText("Total Price: ₹" + totalPrice);
    }

    private void bookFlight() {
        try {
            String from = fromField.getText().trim();
            String to = toField.getText().trim();
            String date = dateField.getText().trim();
            int seats = (int) seatSpinner.getValue();

            if (from.isEmpty() || to.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }

            Conn c = new Conn();

            String sql = "INSERT INTO flight_booking (from_city, to_city, journey_date, seats, price) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.setString(1, from);
            ps.setString(2, to);
            ps.setString(3, date);
            ps.setInt(4, seats);
            ps.setInt(5, totalPrice);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "✅ Flight Booked Successfully\nTotal Bill: ₹" + totalPrice);

            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error while booking flight");
        }
    }
}
