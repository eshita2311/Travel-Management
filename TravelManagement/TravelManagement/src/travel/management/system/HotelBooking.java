package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;

public class HotelBooking extends JFrame {

    JTextField hotelField, cityField;
    JSpinner daysSpinner, personsSpinner;
    JLabel priceLabel;

    public HotelBooking() {

        setTitle("Hotel Booking");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("🏨 Hotel Booking", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(0, 20, 500, 40);
        add(title);

        addLabel("Hotel Name", 60);
        hotelField = addField(60);

        addLabel("City", 110);
        cityField = addField(110);

        addLabel("Days", 160);
        daysSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        daysSpinner.setBounds(200, 160, 200, 30);
        add(daysSpinner);

        addLabel("Persons", 210);
        personsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        personsSpinner.setBounds(200, 210, 200, 30);
        add(personsSpinner);

        priceLabel = new JLabel("Total Price: ₹0", JLabel.CENTER);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        priceLabel.setBounds(0, 260, 500, 30);
        add(priceLabel);

        JButton book = new JButton("Confirm Hotel");
        book.setBounds(170, 300, 160, 40);
        book.addActionListener(e -> bookHotel());
        add(book);

        daysSpinner.addChangeListener(e -> updatePrice());
        personsSpinner.addChangeListener(e -> updatePrice());

        setVisible(true);
    }

    private void updatePrice() {
        int days = (int) daysSpinner.getValue();
        int persons = (int) personsSpinner.getValue();
        int price = days * persons * 2000;
        priceLabel.setText("Total Price: ₹" + price);
    }

    private void bookHotel() {
        try {
            String hotel = hotelField.getText().trim();
            String city = cityField.getText().trim();
            int days = (int) daysSpinner.getValue();
            int persons = (int) personsSpinner.getValue();
            int totalPrice = days * persons * 2000;

            if (hotel.isEmpty() || city.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields");
                return;
            }

            Conn c = new Conn();
            String sql = "INSERT INTO hotel_booking (hotel_name, city, days, persons, price) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.setString(1, hotel);
            ps.setString(2, city);
            ps.setInt(3, days);
            ps.setInt(4, persons);
            ps.setInt(5, totalPrice);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "✅ Hotel Booked Successfully\nTotal Bill: ₹" + totalPrice);

            dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error while booking hotel");
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
