package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;

public class TransportationBooking extends JFrame {

    JTextField fromField, toField, distanceField;
    JComboBox<String> typeBox;
    JLabel priceLabel;

    public TransportationBooking() {

        setTitle("Transport Booking");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("🚗 Transport Booking", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(0, 20, 500, 40);
        add(title);

        addLabel("Transport Type", 80);
        typeBox = new JComboBox<>(new String[] { "Car", "Bus", "Train" });
        typeBox.setBounds(200, 80, 200, 30);
        add(typeBox);

        addLabel("From City", 130);
        fromField = addField(130);

        addLabel("To City", 180);
        toField = addField(180);

        addLabel("Distance (km)", 230);
        distanceField = addField(230);

        priceLabel = new JLabel("Total Price: ₹0", JLabel.CENTER);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        priceLabel.setBounds(0, 270, 500, 30);
        add(priceLabel);

        JButton book = new JButton("Confirm Transport");
        book.setBounds(170, 310, 160, 40);
        book.addActionListener(e -> bookTransport());
        add(book);

        distanceField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                updatePrice();
            }
        });

        setVisible(true);
    }

    private void updatePrice() {
        try {
            int distance = Integer.parseInt(distanceField.getText());
            int price = distance * 15;
            priceLabel.setText("Total Price: ₹" + price);
        } catch (Exception ignored) {
            priceLabel.setText("Total Price: ₹0");
        }
    }

    private void bookTransport() {
        try {
            String type = typeBox.getSelectedItem().toString();
            String from = fromField.getText().trim();
            String to = toField.getText().trim();
            int distance = Integer.parseInt(distanceField.getText().trim());
            int price = distance * 15;

            Conn c = new Conn();
            String sql = "INSERT INTO transport_booking (transport_type, from_city, to_city, distance, price) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.setString(1, type);
            ps.setString(2, from);
            ps.setString(3, to);
            ps.setInt(4, distance);
            ps.setInt(5, price);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "✅ Transport Booked Successfully\nTotal Bill: ₹" + price);

            dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error while booking transport");
        }
    }

    private void addLabel(String text, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(80, y, 120, 30);
        add(l);
    }

    private JTextField addField(int y) {
        JTextField t = new JTextField();
        t.setBounds(200, y, 200, 30);
        add(t);
        return t;
    }
}
