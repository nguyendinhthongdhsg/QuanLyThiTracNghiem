package DAL;

import DTO.ExamsDTO;
import config.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ExamsDAL {
    public List<ExamsDTO> getAllExams() {
        List<ExamsDTO> examList = new ArrayList<>();
        String sql = "SELECT * FROM exams";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                examList.add(mapResultSetToExamDTO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examList;
    }

    public ExamsDTO getExamById(int examId) {  
        String sql = "SELECT * FROM exams WHERE examId = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToExamDTO(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addExam(ExamsDTO exam) {
         if (isExamCodeExists(exam.getExCode())) {
        JOptionPane.showMessageDialog(null, "Mã đề thi đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
        String sql = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getTestCode());
            ps.setString(2, exam.getExOrder());
            ps.setString(3, exam.getExCode());
            ps.setString(4, exam.getExQuestIDs());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateExam(ExamsDTO exam) {
        String sql = "UPDATE exams SET testCode = ?, exOrder = ?, exCode = ?, ex_quesIDs = ? WHERE examId = ?";
        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getTestCode());
            ps.setString(2, exam.getExOrder());
            ps.setString(3, exam.getExCode());
            ps.setString(4, exam.getExQuestIDs());
            ps.setInt(5, exam.getExamId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteExam(int examId) { 
        String sql = "DELETE FROM exams WHERE examId = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ExamsDTO> searchExam(String keyword) {
        List<ExamsDTO> examList = new ArrayList<>();
        String sql = "SELECT * FROM exams WHERE testCode LIKE ? OR exCode LIKE ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    examList.add(mapResultSetToExamDTO(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examList;
    }

    private ExamsDTO mapResultSetToExamDTO(ResultSet rs) throws SQLException {
        return new ExamsDTO(
                rs.getInt("examId"),
                rs.getString("testCode"),
                rs.getString("exOrder"),
                rs.getString("exCode"),
                rs.getString("ex_quesIDs")
        );
    }
    
    public boolean isExamCodeExists(String exCode) {
        String query = "SELECT COUNT(*) FROM exams WHERE exCode = ?";
            try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, exCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<ExamsDTO> getExamsByTestCode(String testCode) {
        List<ExamsDTO> exams = new ArrayList<>();
        String sql = "SELECT * FROM exams WHERE testCode = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, testCode);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ExamsDTO exam = new ExamsDTO(
                        rs.getInt("examId"),
                        rs.getString("testCode"),
                        rs.getString("exOrder"),
                        rs.getString("exCode"),
                        rs.getString("ex_quesIDs")
                    );
                    exams.add(exam);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }


    
}
