package dao;

import dao.singleton.UserSingleton;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends GenericDAO {

    User user;

    public UserDAO() {
        super();
        user = UserSingleton.getInstance();
    }

    public User checkUserLogin(String email, String password) {
        try {
            String sql = "select * from Users where (UserEmail='"+email+"' OR UserPhone='"+email+"') AND UserPassword='"+password+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userEmail = rs.getString(3);
                String userPhone = rs.getString(4);
                String userPassword = rs.getString(5);
                if((userEmail.equals(email) || userPhone.equals(email)) && userPassword.equals(password)) {
                    user.setId(rs.getString(1));
                    user.setName(rs.getString(2));
                    user.setEmail(userEmail);
                    user.setPhone(userPhone);
                    user.setPassword(userPassword);
                    user.setGender(rs.getString(6));
                    user.setAddress(rs.getString(7));
                    user.setRole( rs.getString(8));
                    return user;
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewUser(String id,String name, String email, String address,
                              String phone, String password, String gender, String role) {
        try {
            String sql = "insert into Users values('"+ id+"', " +
                    "'"+ name +"', " +
                    "'"+ email +"', " +
                    "'"+ phone +"', " +
                    "'"+ password +"', " +
                    "'"+ gender +"', " +
                    "'"+ address +"', " +
                    "'"+ role +"')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserData(String id,String name, String email, String address,
                               String phone, String gender) {
        try {
            String sql = "update Users set UserFullName='" + name +
                    "', UserPhone='" + phone +
                    "', UserEmail='" + email +
                    "', UserGender='" + gender +
                    "', UserAddress='" + address +
                    "' where UserID='"+id+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserPassword(String id, String password) {
        try {
            String sql = "update Users set UserPassword='" + password +
                    "' where UserID='"+id+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
