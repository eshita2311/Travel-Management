package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login() {

        setTitle("Tourist Login");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // ================= LEFT PANEL (IMAGE) =================
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 350, 450);
        leftPanel.setBackground(new Color(135, 206, 235));
        leftPanel.setLayout(null);

        ImageIcon icon = new ImageIcon(
                ClassLoader.getSystemResource(
                        "travel/management/system/icons/user.png"));
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(75, 120, 200, 200);

        leftPanel.add(imageLabel);
        add(leftPanel);

        // ================= RIGHT PANEL (FORM) =================
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(350, 0, 450, 450);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        JLabel title = new JLabel("Tourist Login");
        title.setBounds(130, 40, 300, 40);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        rightPanel.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(80, 120, 200, 25);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rightPanel.add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(80, 150, 280, 35);
        rightPanel.add(username);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(80, 200, 200, 25);
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rightPanel.add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(80, 230, 280, 35);
        rightPanel.add(password);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(80, 300, 130, 40);
        loginBtn.setBackground(new Color(0, 120, 215));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setFocusPainted(false);

        loginBtn.addActionListener(e -> {
            if (username.getText().isEmpty() ||
                    password.getPassword().length == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // OPEN DASHBOARD
                new Dashboard();
                setVisible(false);
            }
        });

        rightPanel.add(loginBtn);

        add(rightPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
