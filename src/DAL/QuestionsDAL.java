package DAL;

import DTO.QuestionsDTO;
import DTO.AnswersDTO;
import config.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAL {

    // Lấy danh sách tất cả câu hỏi
    public List<QuestionsDTO> getAllQuestions() {
        List<QuestionsDTO> questionList = new ArrayList<>();
        String sql = "SELECT * FROM questions";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                QuestionsDTO question = new QuestionsDTO(
                    rs.getInt("qID"),
                    rs.getString("qContent"),
                    rs.getString("qPictures"),
                    rs.getInt("qTopicID"),
                    rs.getString("qLevel"),
                    rs.getByte("qStatus"),
                    new AnswersDAL().getAnswersByQuestionID(rs.getInt("qID"))
                );
                questionList.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }
    
   public QuestionsDTO getQuestionById(int id) {
    String query = "SELECT * FROM questions WHERE qID = ?";
    
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new QuestionsDTO(
                    rs.getInt("qID"),
                    rs.getString("qContent"),
                    rs.getString("qPictures"), 
                    rs.getInt("qTopicID"),
                    rs.getString("qLevel"),
                    rs.getByte("qStatus"),
                    new AnswersDAL().getAnswersByQuestionID(rs.getInt("qID"))
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    // Thêm mới câu hỏi
   public boolean addQuestion(QuestionsDTO question) {
    String sql = "INSERT INTO questions (qContent, qPictures, qTopicID, qLevel, qStatus) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, question.getqContent());
        ps.setString(2, question.getqPictures() != null ? question.getqPictures() : null);
        ps.setInt(3, question.getqTopicID());
        ps.setString(4, question.getqLevel());
        ps.setByte(5, question.getqStatus());

        return ps.executeUpdate() > 0;  
    } catch (SQLException e) {
        e.printStackTrace();
        return false;  
    }
}





    // Cập nhật câu hỏi
    public boolean updateQuestion(QuestionsDTO question) {
        String sql = "UPDATE questions SET qContent = ?, qPictures = ?, qTopicID = ?, qLevel = ?, qStatus = ? WHERE qID = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, question.getqContent());
            ps.setString(2, question.getqPictures());
            ps.setInt(3, question.getqTopicID());
            ps.setString(4, question.getqLevel());
            ps.setByte(5, question.getqStatus());
            ps.setInt(6, question.getqID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa câu hỏi
    public boolean deleteQuestion(int questionID) {
    String deleteAnswersSQL = "DELETE FROM answers WHERE qID = ?";
    String deleteQuestionSQL = "DELETE FROM questions WHERE qID = ?";

    try (Connection conn = MySQLConnection.getConnection()) {
        conn.setAutoCommit(false); 
        
        try (PreparedStatement psAnswers = conn.prepareStatement(deleteAnswersSQL)) {
            psAnswers.setInt(1, questionID);
            psAnswers.executeUpdate();
        }

        try (PreparedStatement psQuestion = conn.prepareStatement(deleteQuestionSQL)) {
            psQuestion.setInt(1, questionID);
            boolean result = psQuestion.executeUpdate() > 0;
            conn.commit(); 
            return result;
        } catch (SQLException e) {
            conn.rollback(); 
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }


    // Tìm kiếm câu hỏi theo nội dung hoặc ID
    public List<QuestionsDTO> searchQuestion(String keyword) {
        List<QuestionsDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE qContent LIKE ? OR qID = ?";
        
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, keyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    QuestionsDTO question = new QuestionsDTO(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel"),
                        rs.getByte("qStatus"),
                        new AnswersDAL().getAnswersByQuestionID(rs.getInt("qID"))
                    );
                    result.add(question);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
