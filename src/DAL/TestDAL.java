package DAL;

import DTO.TestDTO;
import config.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class TestDAL {
    // Lấy tất cả bài thi
    public List<TestDTO> getAllTests() {
        List<TestDTO> testList = new ArrayList<>();
        String sql = "SELECT * FROM test";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                testList.add(mapResultSetToTestDTO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    // Lấy bài thi theo ID
    public TestDTO getTestByID(int testID) {
        String sql = "SELECT * FROM test WHERE testID = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, testID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTestDTO(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách bài thi theo chủ đề (topicID)
    public List<TestDTO> getTestsByTopicID(int topicID) {
        List<TestDTO> testList = new ArrayList<>();
        String sql = "SELECT * FROM test WHERE tpID = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, topicID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    testList.add(mapResultSetToTestDTO(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    // Kiểm tra xem chủ đề có tồn tại không
    public boolean isTopicExist(int topicID) {
        String sql = "SELECT COUNT(*) FROM topics WHERE tpID = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, topicID);
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

    // Thêm bài thi mới
    public boolean addTest(TestDTO test) {
    int nextTestID = getNextTestID();
    String sql = "INSERT INTO test (testID, testCode, testTitle, testTime, tpID, num_easy, num_medium, num_diff, test_limit, testDate, testStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, nextTestID);
        ps.setString(2, test.getTestCode());
        ps.setString(3, test.getTestTitle());
        ps.setInt(4, test.getTestTime());
        ps.setInt(5, test.getTpID());
        ps.setInt(6, test.getNumEasy());
        ps.setInt(7, test.getNumMedium());
        ps.setInt(8, test.getNumDiff());
        ps.setInt(9, test.getTestLimit());
        String testDate = LocalDate.now().toString(); 
        ps.setString(10, testDate);
        ps.setInt(11, test.getTestStatus());
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }

    // Lấy ID tiếp theo cho bài thi
    private int getNextTestID() {
        String sql = "SELECT MAX(testID) FROM test";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    // Cập nhật bài thi
    public boolean updateTest(TestDTO test) {
    String sql = "UPDATE test SET testCode = ?, testTitle = ?, testTime = ?, tpID = ?, num_easy = ?, num_medium = ?, num_diff = ?, test_limit = ?, testDate = ?, testStatus = ? WHERE testID = ?";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, test.getTestCode());
        ps.setString(2, test.getTestTitle());
        ps.setInt(3, test.getTestTime());
        ps.setInt(4, test.getTpID());
        ps.setInt(5, test.getNumEasy());
        ps.setInt(6, test.getNumMedium());
        ps.setInt(7, test.getNumDiff());
        ps.setInt(8, test.getTestLimit());
        ps.setString(9, test.getTestDate());
        ps.setInt(10, test.getTestStatus());
        ps.setInt(11, test.getTestID());

        int rowsUpdated = ps.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated);
        return rowsUpdated > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // Xóa bài thi theo ID
    public boolean deleteTest(int testID) {
        String sql = "DELETE FROM test WHERE testID = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, testID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Đếm số lượng bài thi theo chủ đề
    public int countTestsByTopicID(int topicID) {
        String sql = "SELECT COUNT(*) FROM test WHERE tpID = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, topicID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tìm kiếm bài thi theo từ khóa (testCode hoặc testTitle)
    public List<TestDTO> searchTests(String keyword) {
        List<TestDTO> testList = new ArrayList<>();
        String sql = "SELECT * FROM test WHERE testCode LIKE ? OR testTitle LIKE ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    testList.add(mapResultSetToTestDTO(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    // Hàm tiện ích để map ResultSet thành TestDTO
    private TestDTO mapResultSetToTestDTO(ResultSet rs) throws SQLException {
        TestDTO test = new TestDTO();
        test.setTestID(rs.getInt("testID"));
        test.setTestCode(rs.getString("testCode"));
        test.setTestTitle(rs.getString("testTitle"));
        test.setTestTime(rs.getInt("testTime"));
        test.setTpID(rs.getInt("tpID"));
        test.setNumEasy(rs.getInt("num_easy"));
        test.setNumMedium(rs.getInt("num_medium"));
        test.setNumDiff(rs.getInt("num_diff"));
        test.setTestLimit(rs.getInt("test_limit"));
        test.setTestDate(rs.getString("testDate"));
        test.setTestStatus(rs.getInt("testStatus"));
        return test;
    }
}
