package DAL;

import DTO.UsersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.MySQLConnection;

public class UsersDAL {

    public boolean registerUser(UsersDTO user) {
        String sql = "INSERT INTO Users (userName, userEmail, userPassword, userFullName, isAdmin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = MySQLConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserEmail());
            ps.setString(3, user.getUserPassword());
            ps.setString(4, user.getUserFullName());
            ps.setByte(5, user.getIsAdmin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkUserExists(String userName) {
        String sql = "SELECT COUNT(*) FROM Users WHERE userName = ?";
        try (Connection conn = MySQLConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
   public static UsersDTO selectByAccount(String username, String password) {
    UsersDTO user = null;
    String query = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
    
    try (Connection conn = MySQLConnection.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, username);
        ps.setString(2, password);

        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                user = new UsersDTO();
                user.setUserName(resultSet.getString("userName"));
                user.setUserEmail(resultSet.getString("userEmail"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setUserFullName(resultSet.getString("userFullName"));
                user.setIsAdmin(resultSet.getByte("isAdmin"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;  // Trả về user nếu tìm thấy, hoặc null nếu không
}

}
