package dao;

import model.Fish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class FishDAO extends GenericDAO {

    public FishDAO() {
        super();
    }

    public Vector<Vector<String>> getAllFish() {
        Vector<Vector<String>> fishData = new Vector<>();
        try {
            String sql = "select * from Fish";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> fishVector = new Vector<>();
                Fish fish = new Fish(rs.getString(1), rs.getString(2),
                                    rs.getString(3), rs.getInt(4),
                                    rs.getInt(5));
                fishVector.add(fish.getFishId());
                fishVector.add(fish.getFishName());
                fishVector.add(fish.getFishType());
                fishVector.add(String.valueOf(fish.getFishPrice()));
                fishVector.add(String.valueOf(fish.getFishStock()));
                fishData.add(fishVector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fishData;
    }

    public void insertFish(String fishId, String name, String type, int price, int stock) {
        try {
            String sql = "insert into Fish values('"+ fishId +"', " +
                    "'"+ name +"', " +
                    "'"+ type +"', " +
                    ""+ price +", " +
                    ""+ stock +")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFish(String fishId, String name, String type, int price) {
        try {
            String sql = "update Fish set FishName='" + name + "', " +
                    "FishType='" + type + "', " +
                    "FishPrice='" + price + "' " +
                    " where FishID='"+fishId+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFish(String fishId) {
        try {
            String sql = "DELETE From Fish WHERE FishID='"+fishId+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseFishStock(String fishId, int qty) {
        try {
            String sql = "update Fish set FishStock=FishStock + " + qty +
                    " where FishID='"+fishId+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reduceFishStock(String fishId, int qty) {
        try {
            String sql = "update Fish set FishStock=FishStock - " + qty +
                    " where FishID='"+fishId+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
