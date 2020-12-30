package dao;

import model.Cart;
import model.Fish;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CartDAO extends GenericDAO {

    FishDAO fishDAO;

    public CartDAO() {
        super();
        fishDAO = new FishDAO();
    }

    public Vector<Vector<String>> getUserCartData(String userId) {
        Vector<Vector<String>> cartData = new Vector<>();
        try {
            String sql = "select * from Cart c JOIN Fish f ON f.FishId=c.FishId " +
                    "WHERE UserID='" + userId +"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cart cart = new Cart();
                Fish fish = new Fish();
                cart.setFishId(rs.getString(2));
                cart.setQty(rs.getInt(3));
                fish.setFishId(rs.getString(4));
                fish.setFishName(rs.getString(5));
                fish.setFishType(rs.getString(6));
                fish.setFishPrice(rs.getInt(7));
                fish.setFishStock(rs.getInt(8));
                Vector<String> rowData = new Vector<>();
                rowData.add(cart.getFishId());
                rowData.add(fish.getFishName());
                rowData.add(fish.getFishType());
                rowData.add(String.valueOf(fish.getFishPrice()));
                rowData.add(String.valueOf(fish.getFishStock()));
                rowData.add(String.valueOf(cart.getQty()));
                rowData.add(String.valueOf(cart.getQty() * fish.getFishPrice()));
                cartData.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartData;
    }

    public void addToCart(String userId, String fishId, int qty) {
        try {
            String sql = "insert into Cart values(" +
                    "'"+ userId +"', " +
                    "'"+ fishId +"', " +
                    ""+ qty +")";
            stmt.executeUpdate(sql);
            fishDAO.reduceFishStock(fishId, qty);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something's wrong, please try again");
        }
    }

    public void updateCartQuantity(String fishId, String userId, int qty) {
        try {
            String sql = "update Cart set Quantity= Quantity +" + qty +
                    " WHERE FishID='"+fishId+"'" + "AND UserID='"+userId+"'";
            stmt.executeUpdate(sql);
            fishDAO.reduceFishStock(fishId, qty);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCart(String fishId, String userId) {
        try {
            String sql = "select FishID, UserID from Cart WHERE FishID='"+fishId+"'" +
                    "AND UserID='"+userId+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String theFishId = rs.getString(1);
                String theUserId = rs.getString(2);
                if(fishId.equals(theFishId) && userId.equals(theUserId)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something's wrong, please try again");
        }
        return false;
    }

    public void deleteCart(String userId, String fishId, int qty) {
        try {
            String sql = "DELETE from Cart WHERE FishID='"+fishId+"'" +
                    "AND UserID='"+userId+"'";
            stmt.executeUpdate(sql);
            fishDAO.increaseFishStock(fishId, qty);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCart(String userId) {
        Vector<Vector<String>> userCart = getUserCartData(userId);
        for(int i = 0; i < userCart.size(); i++) {
            deleteCart(userId, userCart.get(i).get(0),
                    Integer.parseInt(userCart.get(i).get(5)));
        }
    }

}
