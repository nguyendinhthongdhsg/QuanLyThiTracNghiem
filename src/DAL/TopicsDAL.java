package DAL;

import DTO.TopicsDTO;
import config.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicsDAL {

    // Lấy danh sách tất cả chủ đề
    public List<TopicsDTO> getAllTopics() {
        List<TopicsDTO> topicList = new ArrayList<>();
        String sql = "SELECT * FROM topics";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TopicsDTO topic = new TopicsDTO(
                    rs.getInt("tpID"),
                    rs.getString("tpTitle"),
                    rs.getInt("tpParent"),
                    rs.getByte("tpStatus")
                );
                topicList.add(topic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicList;
    }

    // Thêm chủ đề
    public boolean addTopic(TopicsDTO topic) {
        String sql = "INSERT INTO topics (tpTitle, tpParent, tpStatus) VALUES (?, ?, ?)";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, topic.getTpTitle());
            ps.setInt(2, topic.getTpParent());
            ps.setByte(3, topic.getTpStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật chủ đề
    public boolean updateTopic(TopicsDTO topic) {
        String sql = "UPDATE topics SET tpTitle = ?, tpParent = ?, tpStatus = ? WHERE tpID = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, topic.getTpTitle());
            ps.setInt(2, topic.getTpParent());
            ps.setByte(3, topic.getTpStatus());
            ps.setInt(4, topic.getTpID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa chủ đề
    public boolean deleteTopic(int topicID) {
        String sql = "DELETE FROM topics WHERE tpID = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, topicID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm chủ đề theo tên
    public List<TopicsDTO> searchTopic(String keyword) {
        List<TopicsDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM topics WHERE tpTitle LIKE ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TopicsDTO topic = new TopicsDTO(
                        rs.getInt("tpID"),
                        rs.getString("tpTitle"),
                        rs.getInt("tpParent"),
                        rs.getByte("tpStatus")
                    );
                    result.add(topic);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
