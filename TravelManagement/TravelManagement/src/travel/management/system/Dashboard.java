package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

        public Dashboard() {

                setTitle("Tourist Dashboard");
                setSize(900, 500);
                setLocationRelativeTo(null);
                setLayout(null);
                getContentPane().setBackground(Color.WHITE);

                // ================= TITLE =================
                JLabel title = new JLabel("🌍 Tourist Dashboard", JLabel.CENTER);
                title.setBounds(0, 20, 900, 50);
                title.setFont(new Font("Segoe UI", Font.BOLD, 32));
                title.setForeground(new Color(0, 102, 204));
                add(title);

                // ================= FLIGHT BOOKING =================
                JButton flightBtn = new JButton("✈ Book Flight");
                flightBtn.setBounds(150, 120, 200, 50);
                flightBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                flightBtn.setBackground(new Color(0, 123, 255));
                flightBtn.setForeground(Color.WHITE);
                add(flightBtn);

                flightBtn.addActionListener(e -> new FlightBooking());

                // ================= HOTEL BOOKING =================
                JButton hotelBtn = new JButton("🏨 Book Hotel");
                hotelBtn.setBounds(550, 120, 200, 50);
                hotelBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                hotelBtn.setBackground(new Color(40, 167, 69));
                hotelBtn.setForeground(Color.WHITE);
                add(hotelBtn);

                hotelBtn.addActionListener(e -> new HotelBooking());

                // ================= TRANSPORT BOOKING =================
                JButton transportBtn = new JButton("🚗 Book Transport");
                transportBtn.setBounds(350, 200, 200, 50);
                transportBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                transportBtn.setBackground(new Color(255, 193, 7));
                transportBtn.setForeground(Color.BLACK);
                add(transportBtn);

                transportBtn.addActionListener(e -> new TransportationBooking());

                // ================= BOOKING SUMMARY =================
                JButton summaryBtn = new JButton("📋 Booking Summary");
                summaryBtn.setBounds(350, 300, 200, 50);
                summaryBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                summaryBtn.setBackground(new Color(108, 117, 125));
                summaryBtn.setForeground(Color.WHITE);
                add(summaryBtn);

                summaryBtn.addActionListener(e -> new BookingSummary());

                // ================= LOGOUT =================
                JButton logoutBtn = new JButton("🚪 Logout");
                logoutBtn.setBounds(740, 20, 120, 35);
                logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
                logoutBtn.setBackground(Color.RED);
                logoutBtn.setForeground(Color.WHITE);
                add(logoutBtn);

                logoutBtn.addActionListener(e -> {
                        setVisible(false);
                        new Login();
                });

                setVisible(true);
        }
}
