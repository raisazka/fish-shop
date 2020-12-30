package view;

import style.Font;
import view.validation.AuthValidation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JButton loginButton;
    JLabel signupLabel;
    JTextField emailField;
    JPasswordField passwordField;

    public Login() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Login");
        getContentPane().setBackground(Color.CYAN);
        setSize(450, 300);
        setLayout(new BorderLayout());
        initTitle();
        initForms();
        initButtons();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTitle() {
        JLabel title = new JLabel("Login Form", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(10, 10,10,10));
        title.setSize(this.getWidth(), 50);
        title.setFont(Font.setFont(18));
        add(title, BorderLayout.NORTH);
    }

    private void initForms() {
        JPanel form = new JPanel(new GridLayout(2, 2));
        form.setBackground(Color.cyan);
        form.setSize(this.getWidth(), 100);
        JLabel emailLabel = new JLabel("Email or Phone");
        emailField = new JTextField();
        emailField.setSize(100, 50);
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordField.setSize(100, 50);
        form.add(emailLabel);
        form.add(emailField);
        form.add(passwordLabel);
        form.add(passwordField);
        add(form, BorderLayout.CENTER);
    }

    private void initButtons() {
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
        loginButton = new JButton("Login");
        signupLabel = new JLabel("Signup here", SwingConstants.CENTER);
        buttonsPanel.setBackground(Color.CYAN);
        buttonsPanel.setSize(this.getWidth(), 50);
        buttonsPanel.add(loginButton);
        buttonsPanel.add(signupLabel);
        loginButton.addActionListener(this);
        signupLabel.addMouseListener(signupEvent());
        add(buttonsPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)) {
            AuthValidation.validateLogin(emailField.getText(), passwordField.getText(), this);
        }
    }

    private MouseListener signupEvent() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource().equals(signupLabel)) {
                    setVisible(false);
                    new Register();
                }
            }
        };
    }
}
