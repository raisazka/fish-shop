package view.admin;

import view.Login;
import view.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHome extends JFrame implements ActionListener {

    private JMenuItem profile, logoff, exit, manageFish;

    public AdminHome() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Fish Shop - Admin");
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
        JMenu manageMenu = new JMenu("Manage");

        profile = new JMenuItem("Profile");
        logoff = new JMenuItem("Log off");
        exit = new JMenuItem("Exit");
        userMenu.add(profile);
        userMenu.add(logoff);
        userMenu.add(exit);
        profile.addActionListener(this);
        logoff.addActionListener(this);
        exit.addActionListener(this);

        manageFish = new JMenuItem("Manage Fish");
        manageFish.addActionListener(this);
        manageMenu.add(manageFish);

        menuBar.add(userMenu);
        menuBar.add(manageMenu);

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
        } else if(e.getSource().equals(manageFish)) {
            new ManageFish();
        }
        setVisible(false);
    }
}
