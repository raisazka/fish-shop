package view.user;

import view.Login;
import view.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserHome extends JFrame implements ActionListener {

    private JMenuItem profile, logoff, exit, buy, viewTrans;

    public UserHome() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Fish Shop");
        setSize(800, 500);
        setLayout(new BorderLayout());
        initMenu();
        setBackground();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setBackground() {
        ImageIcon img = new ImageIcon("src/style/assets/background.jpg");
        System.out.println(img.getImage().toString());
        JLabel background = new JLabel(img);
        add(background);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new JMenu("User");
        JMenu transactionMenu = new JMenu("Transaction");

        profile = new JMenuItem("Profile");
        logoff = new JMenuItem("Log off");
        exit = new JMenuItem("Exit");
        profile.addActionListener(this);
        logoff.addActionListener(this);
        exit.addActionListener(this);
        userMenu.add(profile);
        userMenu.add(logoff);
        userMenu.add(exit);

        buy = new JMenuItem("Buy Fish");
        viewTrans = new JMenuItem("View Transaction");
        buy.addActionListener(this);
        viewTrans.addActionListener(this);
        transactionMenu.add(buy);
        transactionMenu.add(viewTrans);

        menuBar.add(userMenu);
        menuBar.add(transactionMenu);

        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(exit)) {
            System.exit(0);
        } else if(e.getSource().equals(logoff)) {
            new Login();
        } else if(e.getSource().equals(profile)){
            new Profile();
        } else if(e.getSource().equals(buy)) {
            new BuyFish();
        } else if(e.getSource().equals(viewTrans)) {
            new TransactionHistory();
        }
        setVisible(false);
    }
}
