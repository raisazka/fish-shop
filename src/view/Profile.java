package view;

import dao.singleton.UserSingleton;
import model.User;
import style.Font;
import view.validation.ProfileValidation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JFrame implements ActionListener {

    JLabel emailLabel, passwordLabel, oldPassLabel, confirmLabel,
            nameLabel, phoneLabel, addressLabel,
            genderLabel;
    JTextField emailField, nameField, phoneField;
    JPasswordField oldPassField, passwordField, confirmField;
    JTextArea addressField;
    ButtonGroup radioGroup;
    JRadioButton maleRadio, femaleRadio;
    JButton updateButton, passwordButton;
    User user;

    public Profile() {
        user = UserSingleton.getInstance();
        initFrame();
    }

    private void initFrame() {
        setTitle("Profile");
        setSize(800, 400);
        GridLayout layout = new GridLayout(1, 2);
        setLayout(layout);
        setBackground(Color.CYAN);
        initProfilePanel();
        initPasswordPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initProfilePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(Color.CYAN);
        panel.setBorder(new EmptyBorder(20, 20, 0, 0));
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.setBackground(Color.CYAN);
        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setFont(Font.setFont(25));

        nameLabel = new JLabel("Full name");
        nameField = new JTextField();
        nameField.setText(user.getName());

        emailLabel = new JLabel("Email or Phone");
        emailField = new JTextField();
        emailField.setText(user.getEmail());

        phoneLabel = new JLabel("Phone");
        phoneField = new JTextField();
        phoneField.setText(user.getPhone());

        addressLabel = new JLabel("Address");
        addressField = new JTextArea();
        addressField.setText(user.getAddress());

        //Radio Buttons
        genderLabel = new JLabel("Gender");
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        radioPanel.setBorder(new EmptyBorder(25, 0, 0,0));
        radioPanel.setBackground(Color.CYAN);
        radioGroup = new ButtonGroup();
        maleRadio = new JRadioButton("Male");
        maleRadio.setActionCommand("Male");
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setActionCommand("Female");

        if(user.getGender().equals("Male")) {
            maleRadio.setSelected(true);
        } else {
            femaleRadio.setSelected(true);
        }

        radioPanel.add(maleRadio);
        radioPanel.add(femaleRadio);
        radioGroup.add(maleRadio);
        radioGroup.add(femaleRadio);

        updateButton = new JButton("Update Profile");
        updateButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.setBorder(new EmptyBorder(20, 0,0,0));
        buttonPanel.add(updateButton);

        formPanel.add(profileLabel);
        formPanel.add(new JLabel(""));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        panel.add(formPanel);
        panel.add(buttonPanel);
        add(panel);
    }

    private void initPasswordPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(Color.CYAN);
        panel.setBorder(new EmptyBorder(30, 10, 0, 20));
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.setBackground(Color.CYAN);
        oldPassLabel = new JLabel("Old Password");
        passwordLabel = new JLabel("New Password");
        confirmLabel = new JLabel("Confirm Password");
        oldPassField = new JPasswordField();
        passwordField = new JPasswordField();
        confirmField = new JPasswordField();
        passwordButton = new JButton("Update Password");
        passwordButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.setBorder(new EmptyBorder(20, 0,0,0));
        buttonPanel.add(passwordButton);

        formPanel.add(oldPassLabel);
        formPanel.add(oldPassField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(confirmLabel);
        formPanel.add(confirmField);
        panel.add(formPanel);
        panel.add(buttonPanel);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(updateButton)) {
            ProfileValidation.validateUpdateProfile(user.getId(),
                    nameField.getText(), emailField.getText(),
                    addressField.getText(), phoneField.getText(),
                    radioGroup.getSelection().getActionCommand());
        } else if(e.getSource().equals(passwordButton)) {
            ProfileValidation.updatePasswordValidation(user.getId(), user.getPassword(),
                    oldPassField.getText(),passwordField.getText(),
                    confirmField.getText());
        }
    }
}
