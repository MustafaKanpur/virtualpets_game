import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

public class Game extends JFrame {

    private JTextField passcode;
    private JPanel panelCont;
    private JPanel passwordScreen;
    private JPanel parentalControlScreen;
    private JButton enter;
    private CardLayout cl;
    private JButton exit;
    private ImageIcon phil;

    public Game() {

        this.passcode = new JTextField();
        this.panelCont = new JPanel();
        this.passwordScreen = new JPanel();
        this.parentalControlScreen = new JPanel();
        this.enter = new JButton();
        this.cl = new CardLayout();
        this.exit = new JButton("Exit");
        this.phil = new ImageIcon("C:\\Users\\owner\\Downloads\\phil_dad.jpg");

        passcode.setPreferredSize(new Dimension(50,50));
        passcode.setMaximumSize(new Dimension(50,50));
        passcode.setAlignmentX(Component.CENTER_ALIGNMENT);

        enter.setText("Enter");
        enter.setAlignmentX(Component.CENTER_ALIGNMENT);
        enter.setBounds(150, 200, 100, 100);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passwordAttempt = passcode.getText();
                if (passwordAttempt.equals("tmtmtm")) {
                    System.out.println("Access Granted");
                    cl.show(panelCont,"2");
                } else {
                    System.out.println("Access Denied");
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cl.show(panelCont,"1");
            }
        });

        panelCont.setLayout(cl);
        passwordScreen.setLayout(new BoxLayout(passwordScreen, BoxLayout.Y_AXIS));

        passwordScreen.add(passcode);
        passwordScreen.add(enter);
        passwordScreen.setBackground(Color.pink);

        parentalControlScreen.setLayout(new GridLayout(0,2));
        parentalControlScreen.setBackground(Color.orange);
        parentalControlScreen.add(this.exit);
        JLabel label = new JLabel(this.phil);
        parentalControlScreen.add(label);

        panelCont.add(passwordScreen,"1");
        panelCont.add(parentalControlScreen,"2");
        cl.show(panelCont,"1");

        this.setTitle("Game");
        this.setPreferredSize(new Dimension(1000,500));
        this.add(panelCont);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.add(panelCont);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }
}
