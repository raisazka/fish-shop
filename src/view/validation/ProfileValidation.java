package view.validation;

import dao.UserDAO;

import javax.swing.*;

public class ProfileValidation extends GenericUserValidation {

    static UserDAO userDAO = new UserDAO();

    public static void validateUpdateProfile(String id,String name, String email, String address,
                                        String phone, String gender) {
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


        if(!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
            JOptionPane.showMessageDialog(null, "Choose Gender");
            return;
        }

        userDAO.updateUserData(id, name, email, address, phone, gender);
        JOptionPane.showMessageDialog(null, "Success Update Profile");
    }

    public static void updatePasswordValidation(String id, String oldPassword,
                                                String oldPasswordInput,
                                                String newPassword, String confirm) {

        if(!oldPasswordInput.equals(oldPassword)) {
            JOptionPane.showMessageDialog(null, "Old password doesn't match");
            return;
        }

        if((newPassword.length() < 8 || newPassword.length() > 20) || checkPassword(newPassword)) {
            JOptionPane.showMessageDialog(null, "Password must be > 8 Characters and Alphanumeric");
            return;
        }

        if(!newPassword.equals(confirm)) {
            JOptionPane.showMessageDialog(null, "Confirm Password must match");
            return;
        }

        userDAO.updateUserPassword(id, newPassword);
        JOptionPane.showMessageDialog(null, "Success Update Password");
    }

}
