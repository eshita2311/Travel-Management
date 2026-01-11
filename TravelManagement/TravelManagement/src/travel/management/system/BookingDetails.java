package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class BookingDetails extends JFrame {

    public BookingDetails() {

        setTitle("📋 Booking Details");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel msg = new JLabel(
                "All booking details will appear here",
                JLabel.CENTER);
        msg.setFont(new Font("Segoe UI", Font.BOLD, 18));

        add(msg, BorderLayout.CENTER);

        setVisible(true);
    }
}
