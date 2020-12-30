package view.validation;

import dao.UserDAO;
import model.User;
import view.Login;
import view.admin.AdminHome;
import view.user.UserHome;

import javax.swing.*;

public class AuthValidation extends GenericUserValidation {

    static UserDAO userDAO = new UserDAO();

    public static void validateLogin(String email, String password, JFrame frame) {

        if(email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email and Password must be filled!");
            return;
        }

        User loggedInUser = userDAO.checkUserLogin(email, password);
        if(loggedInUser == null) {
            JOptionPane.showMessageDialog(null, "Username or Password Not Found");
            return;
        }

        if(loggedInUser.getRole().equalsIgnoreCase("Admin")) {
            new AdminHome();
        } else {
            new UserHome();
        }
        JOptionPane.showMessageDialog(null, "Login Success, Welcome " + loggedInUser.getName() + "!");
        frame.setVisible(false);
    }

    public static void validateRegister(String id,String name, String email, String address, String phone,
                                        String password, String gender, String role, JFrame frame) {
        if(name.length() < 5 || name.length() > 30) {
            JOptionPane.showMessageDialog(null, "Name should be 5 - 30 Characters");
            return;
        }

        if(validateEmail(email)) {
            JOptionPane.showMessageDialog(null, "Please fill a valid email");
            return;
        }

        if((phone.length() < 10 || phone.length() > 12) || checkDigits(phone)) {
            JOptionPane.showMessageDialog(null, "Phone must be 10 - 12 Digits");
            return;
        }

        if(address.length() <= 10 || !address.endsWith("Street")) {
            JOptionPane.showMessageDialog(null, "Address must be 10 Char and ends with Street");
            return;
        }

        if((password.length() < 8 || password.length() > 20) || checkPassword(password)) {
            JOptionPane.showMessageDialog(null, "Password must be > 8 Characters and Alphanumeric");
            return;
        }

        if(!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
            JOptionPane.showMessageDialog(null, "Choose Gender");
            return;
        }

        if(!(role.equalsIgnoreCase("User") || role.equalsIgnoreCase("Admin"))) {
            JOptionPane.showMessageDialog(null, "Select Role");
            return;
        }

        userDAO.insertNewUser(id, name, email, address, phone, password, gender, role);
        JOptionPane.showMessageDialog(null, "Success Register!");
        new Login();
        frame.setVisible(false);
    }

}
