package DAL;

import DTO.AnswersDTO;
import config.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswersDAL {
    
    
    
    public AnswersDTO getAnswerByID(int answerID) {
    String sql = "SELECT * FROM answers WHERE awID = ?";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, answerID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                AnswersDTO answer = new AnswersDTO();
                answer.setAwID(rs.getInt("awID"));
                answer.setqID(rs.getInt("qID"));
                answer.setAwContent(rs.getString("awContent"));
                answer.setAwPictures(rs.getString("awPictures"));
                answer.setIsRight(rs.getBoolean("isRight"));
                answer.setAwStatus(rs.getByte("awStatus"));
                return answer;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public List<AnswersDTO> getAnswersByQuestionID(int questionID) {
        List<AnswersDTO> answerList = new ArrayList<>();
        String sql = "SELECT * FROM answers WHERE qID = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, questionID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AnswersDTO answer = new AnswersDTO();
                    answer.setAwID(rs.getInt("awID"));
                    answer.setqID(rs.getInt("qID"));
                    answer.setAwContent(rs.getString("awContent"));
                    answer.setAwPictures(rs.getString("awPictures"));
                    answer.setIsRight(rs.getBoolean("isRight"));
                    answer.setAwStatus(rs.getByte("awStatus"));
                    answerList.add(answer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answerList;
    }
    public boolean addAnswer(AnswersDTO answer, int questionID) {
    int nextAwID = getNextAwID(questionID);  
    String sql = "INSERT INTO answers (awID, qID, awContent, awPictures, isRight, awStatus) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, nextAwID); 
        ps.setInt(2, questionID);
        ps.setString(3, answer.getAwContent());
        ps.setString(4, answer.getAwPictures());
        ps.setBoolean(5, answer.isRight());
        ps.setByte(6, answer.getAwStatus());
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    public int getNextAwID(int questionID) {
    String sql = "SELECT MAX(awID) FROM answers WHERE qID = ?";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, questionID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) + 1; 
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 1; 
}

    public boolean updateAnswer(AnswersDTO answer, int questionID) {
    String sql = "UPDATE answers SET awContent = ?, awPictures = ?, isRight = ?, awStatus = ? WHERE awID = ? AND qID = ?";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, answer.getAwContent());
        ps.setString(2, answer.getAwPictures());
        ps.setBoolean(3, answer.isRight());
        ps.setByte(4, answer.getAwStatus());
        ps.setInt(5, answer.getAwID());
        ps.setInt(6, questionID);  
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public boolean deleteAnswer(int answerID, int questionID) {
    String sql = "DELETE FROM answers WHERE awID = ? AND qID = ?";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, answerID);
        ps.setInt(2, questionID);

        return ps.executeUpdate() > 0;  
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }

    public int countAnswersByQuestionID(int questionID) {
    String query = "SELECT COUNT(*) FROM answers WHERE qID = ?";
    try (Connection conn = MySQLConnection.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, questionID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

}
