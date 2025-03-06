package DAL;

import DTO.UsersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.MySQLConnection;
import java.util.ArrayList;

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
    
    public boolean update(UsersDTO user) {
        String sql = "UPDATE Users SET userEmail=?, userPassword=?, userFullName=?, isAdmin=? where userID=?";
        try (Connection conn = MySQLConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserEmail());
            ps.setString(2, user.getUserPassword());
            ps.setString(3, user.getUserFullName());
            ps.setInt(4, user.getIsAdmin());
            ps.setInt(5, user.getUserID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(int userID) {
        String sql = "DELETE FROM Users where userID=?";
        try (Connection conn = MySQLConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
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
                    user.setUserID(resultSet.getInt("userID"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setUserEmail(resultSet.getString("userEmail"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                    user.setUserFullName(resultSet.getString("userFullName"));
                    user.setIsAdmin(resultSet.getByte("isAdmin"));
                }
            }
            MySQLConnection.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;  // Trả về user nếu tìm thấy, hoặc null nếu không
    }
   
   public ArrayList<UsersDTO> getUserList() {
       ArrayList<UsersDTO> result = new ArrayList<UsersDTO>();
        try {
            Connection con = (Connection) MySQLConnection.getConnection();
            String sql = "SELECT * FROM users";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int userID = rs.getInt("userID");
                String userName = rs.getString("userName");
                String userEmail = rs.getString("userEmail");
                String userPassword = rs.getString("userPassword");
                String userFullName = rs.getString("userFullName");
                byte isAdmin = rs.getByte("isAdmin");
                UsersDTO user = new UsersDTO(userID, userName, userEmail, userPassword, userFullName, isAdmin);
                result.add(user);
            }
            MySQLConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
   }
   
   
    
    public boolean resetPasword(int userID, String password) {
        String sql = "UPDATE Users SET userPassword=? where userID=?";
        try (Connection conn = MySQLConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setInt(2, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void saveUserAction() {
        
    }
}
