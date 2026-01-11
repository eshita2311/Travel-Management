package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {

    Thread t;

    public Splash() {

        setLayout(null);

        ImageIcon i1 = new ImageIcon(
                ClassLoader.getSystemResource(
                        "travel/management/system/icons/travel.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 500);
        add(image);

        JLabel text = new JLabel("Explore the World with Us ✈️");
        text.setBounds(230, 400, 500, 40);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Segoe UI", Font.BOLD, 30));
        image.add(text);

        setSize(900, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            Thread.sleep(3000);
            setVisible(false);
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
