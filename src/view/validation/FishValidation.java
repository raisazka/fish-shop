package view.validation;

import dao.FishDAO;

import javax.swing.*;

public class FishValidation {

    static FishDAO fishDAO = new FishDAO();

    public static void validateFish(String fishId, String fishName, String fishType, int fishPrice) {
        if(fishId.equals("")|| fishId.isEmpty() || fishId.isBlank()) {
            JOptionPane.showMessageDialog(null, "Please select fish to update",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(fishName.length() < 3 || fishName.length() > 25) {
            JOptionPane.showMessageDialog(null, "Fish Name should be 3-25 characters",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(!(fishType.equals("Brackish") || fishType.equals("Seawater") || fishType.equals("Freshwater"))) {
            JOptionPane.showMessageDialog(null, "Select Fish Type",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(fishPrice < 1000 || fishPrice > 10000000) {
            JOptionPane.showMessageDialog(null, "Fish price between 1,000 - 10,000,000",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        fishDAO.updateFish(fishId, fishName, fishType, fishPrice);
        JOptionPane.showMessageDialog(null, "Sucessfully Update Fish Data");
    }

    public static void validateFish(String fishId, String fishName, String fishType, int fishPrice, int fishStock) {
        if(fishId.equals("")|| fishId.isEmpty() || fishId.isBlank()) {
            JOptionPane.showMessageDialog(null, "Please select fish to update",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(fishName.length() < 3 || fishName.length() > 25) {
            JOptionPane.showMessageDialog(null, "Fish Name should be 3-25 characters",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(!(fishType.equals("Brackish") || fishType.equals("Seawater") || fishType.equals("Freshwater"))) {
            JOptionPane.showMessageDialog(null, "Select Fish Type",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(fishPrice < 1000 || fishPrice > 10000000) {
            JOptionPane.showMessageDialog(null, "Fish price between 1,000 - 10,000,000",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(fishStock < 1 || fishStock > 100) {
            JOptionPane.showMessageDialog(null, "Fish stock between 1 - 100",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        fishDAO.insertFish(fishId, fishName, fishType, fishPrice, fishStock);
        JOptionPane.showMessageDialog(null, "Sucessfully Insert New Fish");
    }

    public static void validateRemove(String fishId) {
        if(fishId.equals("")|| fishId.isEmpty() || fishId.isBlank()) {
            JOptionPane.showMessageDialog(null, "Please select fish to remove",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        fishDAO.removeFish(fishId);
        JOptionPane.showMessageDialog(null, "Fish successfully removed");
    }

    public static void validateStock(String fishId, int qty) {

        if(fishId.equals("")|| fishId.isEmpty() || fishId.isBlank()) {
            JOptionPane.showMessageDialog(null, "Please select fish to remove",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(qty < 1 || qty > 100) {
            JOptionPane.showMessageDialog(null, "Fish stock between 1 - 100",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        fishDAO.increaseFishStock(fishId, qty);
        JOptionPane.showMessageDialog(null, "Sucessfully Update Fish Stock");
    }

}
