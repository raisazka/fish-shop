package view;

import helper.RandomString;
import style.Font;
import view.validation.AuthValidation;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Register extends JFrame implements ActionListener {

    JLabel idLabel, emailLabel, passwordLabel,
            nameLabel, phoneLabel, addressLabel,
            genderLabel, roleLabel, signinLabel;
    JTextField idField, emailField,
                nameField, phoneField;
    JTextArea addressField;
    JPasswordField passwordField;
    ButtonGroup radioGroup;
    JRadioButton maleRadio, femaleRadio;
    JComboBox<String> cmbRole;
    JButton registerButton;

    static String userId = "US-" + RandomString.generateAlphanum();


    public Register() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Register");
        getContentPane().setBackground(Color.CYAN);
        setSize(550, 750);
        setLayout(new BorderLayout());
        initTitle();
        initForms();
        initButtons();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTitle() {
        JLabel title = new JLabel("Register Form", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(10, 30,10,30));
        title.setFont(Font.setFont(18));
        add(title, BorderLayout.NORTH);
    }

    private void initForms() {
        JPanel form = new JPanel(new GridLayout(0, 2));
        form.setBackground(Color.cyan);
        form.setBorder(new EmptyBorder(10, 10, 10,10));
        form.setSize(this.getWidth(), 400);
        idLabel = new JLabel("ID");
        idField = new JTextField();
        idField.setEnabled(false);
        idField.setText(userId);
        nameLabel = new JLabel("Full name");
        nameField = new JTextField();
        emailLabel = new JLabel("Email or Phone");
        emailField = new JTextField();
        phoneLabel = new JLabel("Phone");
        phoneField = new JTextField();
        addressLabel = new JLabel("Address");
        addressField = new JTextArea();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        //Radio Buttons
        genderLabel = new JLabel("Gender");
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        radioPanel.setBorder(new EmptyBorder(25, 0, 0,0));
        radioPanel.setBackground(Color.CYAN);
        radioGroup = new ButtonGroup();
        maleRadio = new JRadioButton("Male");
        maleRadio.setActionCommand("Male");
        maleRadio.setSelected(true);
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setActionCommand("Female");
        radioPanel.add(maleRadio);
        radioPanel.add(femaleRadio);
        radioGroup.add(maleRadio);
        radioGroup.add(femaleRadio);

        roleLabel = new JLabel("Role");
        cmbRole = new JComboBox<>();
        cmbRole.addItem("---- Select Role ----");
        cmbRole.addItem("User");
        cmbRole.addItem("Admin");
        cmbRole.addItemListener(e -> {
            if(cmbRole.getSelectedIndex() > 0) {
                System.out.println(cmbRole.getSelectedItem());
            }
        });

        form.add(idLabel);
        form.add(idField);
        form.add(nameLabel);
        form.add(nameField);
        form.add(emailLabel);
        form.add(emailField);
        form.add(phoneLabel);
        form.add(phoneField);
        form.add(addressLabel);
        form.add(addressField);
        form.add(passwordLabel);
        form.add(passwordField);
        form.add(genderLabel);
        form.add(radioPanel);
        form.add(roleLabel);
        form.add(cmbRole);

        add(form, BorderLayout.CENTER);
    }

    private void initButtons() {
        JPanel buttonPanels = new JPanel();
        buttonPanels.setBackground(Color.CYAN);
        buttonPanels.setLayout(new BoxLayout(buttonPanels, BoxLayout.Y_AXIS));
        registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(this);

        signinLabel = new JLabel("Signin Here", SwingConstants.CENTER);
        signinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signinLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanels.add(registerButton);
        buttonPanels.add(signinLabel);
        buttonPanels.setBorder(new EmptyBorder(5, 10, 20, 10));
        add(buttonPanels, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(registerButton)) {
            AuthValidation.validateRegister(userId, nameField.getText(), emailField.getText(),
                    addressField.getText(), phoneField.getText(), passwordField.getText(),
                    radioGroup.getSelection().getActionCommand(),
                    Objects.requireNonNull(cmbRole.getSelectedItem()).toString(), this);
        }
    }
}
