package dao;

import model.Cart;
import model.Fish;
import model.TransactionDetail;
import model.TransactionHeader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TransactionDAO extends GenericDAO {

    CartDAO cartDAO;
    public static int grandTotal = 0;

    public TransactionDAO() {
        super();
        cartDAO = new CartDAO();
    }

    public Vector<Vector<String>> getTransactionData(String userId) {
        Vector<Vector<String>> transactionData = new Vector<>();
        try {
            String sql = "select * from HeaderTransaction WHERE UserID='"+userId+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TransactionHeader header = new TransactionHeader();
                header.setTransactionId(rs.getString(1));
                header.setUserId(rs.getString(2));
                header.setTransactionDate(rs.getString(3));
                Vector<String> row = new Vector<>();
                row.add(header.getTransactionId());
                row.add(header.getUserId());
                row.add(header.getTransactionDate());
                transactionData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionData;
    }

    public Vector<Vector<String>> getTransactionDetailData(String transactionId) {
        Vector<Vector<String>> cartData = new Vector<>();
        try {
            String sql = "select * from DetailTransaction t JOIN Fish f ON f.FishId=t.FishId " +
                    "WHERE TransactionID='" + transactionId +"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TransactionDetail transactionDetail = new TransactionDetail();
                Fish fish = new Fish();
                transactionDetail.setTransactionId(rs.getString(1));
                transactionDetail.setFishId(rs.getString(2));
                transactionDetail.setQty(rs.getInt(3));
                fish.setFishId(rs.getString(4));
                fish.setFishName(rs.getString(5));
                fish.setFishType(rs.getString(6));
                fish.setFishPrice(rs.getInt(7));
                fish.setFishStock(rs.getInt(8));
                Vector<String> rowData = new Vector<>();
                rowData.add(transactionDetail.getTransactionId());
                rowData.add(transactionDetail.getFishId());
                rowData.add(fish.getFishName());
                rowData.add(fish.getFishType());
                rowData.add(String.valueOf(fish.getFishPrice()));
                rowData.add(String.valueOf(transactionDetail.getQty()));
                int subtotal = transactionDetail.getQty() * fish.getFishPrice();
                rowData.add(String.valueOf(subtotal));
                cartData.add(rowData);
                grandTotal += subtotal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartData;
    }

    public void insertNewTransaction(String userId) {
        Vector<Vector<String>> cartData = cartDAO.getUserCartData(userId);
        String transId = insertTransactionHeader(userId);
        for (Vector<String> cartDatum : cartData) {
            insertTransactionDetail(transId, cartDatum.get(0),
                    Integer.parseInt(cartDatum.get(5)));
        }
        cartDAO.deleteAllCart(userId);
    }

    private String insertTransactionHeader(String userId) {
        String transId = generateTransactionId();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String sql = "insert into HeaderTransaction values('"+transId+"', " +
                    "'"+ userId +"', " +
                    "'"+ sqlDate +"')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transId;
    }

    private void insertTransactionDetail(String transId, String fishId, int qty) {
        try {
            String sql = "insert into DetailTransaction values('"+transId+"', " +
                    "'"+ fishId +"', " +
                    ""+ qty +")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateTransactionId() {
        String newId = "";
        String oldId = "";
        try {
            String sql = "select TransactionID from HeaderTransaction ORDER BY TransactionID DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.isBeforeFirst() ) {
                newId = "HT001";
            } else {
                while (rs.next()) {
                    oldId = rs.getString(1);
                }
                int latestId = Integer.parseInt(oldId.replaceAll("[^0-9]", ""));
                newId = String.format("%03d", latestId + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

}
