package view.validation;

import dao.CartDAO;
import dao.TransactionDAO;
import dao.singleton.UserSingleton;
import model.User;

import javax.swing.*;

public class CartValidation {

    static User user = UserSingleton.getInstance();
    static CartDAO cartDAO = new CartDAO();
    static TransactionDAO transDAO = new TransactionDAO();

    public static void validateAddToCart(String fishId, int qty, int stock) {
        if(fishId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose a fish first!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(qty <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(stock == 0) {
            JOptionPane.showMessageDialog(null, "There is no stock left",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(qty > stock) {
            JOptionPane.showMessageDialog(null, "Stock is lesser than quantity",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
         }

        if(user.getId().isEmpty() || user.getId() == null) {
            JOptionPane.showMessageDialog(null, "Something wrong happened",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(cartDAO.checkCart(fishId, user.getId())) {
            cartDAO.updateCartQuantity(fishId, user.getId(), qty);
            JOptionPane.showMessageDialog(null, "Quantity Successfully Updated");
            return;
        }

        cartDAO.addToCart(user.getId(), fishId, qty);
        JOptionPane.showMessageDialog(null, "Fish added to cart!");
    }

    public static void validateDeleteCart(String fishId, int qty) {
        if(fishId == null || fishId.isEmpty() || qty == 0) {
            JOptionPane.showMessageDialog(null, "Select fish you want to remove first",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        cartDAO.deleteCart(user.getId(), fishId, qty);
        JOptionPane.showMessageDialog(null, "Fish Successfully Remove");
    }

    public static void validateTransaction() {
        if(cartDAO.getUserCartData(user.getId()).isEmpty() ||
                cartDAO.getUserCartData(user.getId()).size() == 0) {
            JOptionPane.showMessageDialog(null, "Please add fish to your cart first",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        transDAO.insertNewTransaction(user.getId());
        JOptionPane.showMessageDialog(null, "Checkout Success");
    }

}
