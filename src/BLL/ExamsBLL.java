package BLL;

import DAL.ExamsDAL;
import DTO.ExamsDTO;
import DTO.AnswersDTO;
import DTO.QuestionsDTO;
import config.MySQLConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ExamsBLL {
    private final ExamsDAL examsDAL;

    public ExamsBLL() {
        this.examsDAL = new ExamsDAL();
    }

    public List<ExamsDTO> getAllExams() {
        return examsDAL.getAllExams();
    }

    public ExamsDTO getExamById(int examId) {  
        return examsDAL.getExamById(examId);
    }

    public boolean addExam(ExamsDTO exam) {
        return examsDAL.addExam(exam);
    }

    public boolean updateExam(ExamsDTO exam) {
        return examsDAL.updateExam(exam);
    }

    public boolean deleteExam(int examId) {  
        return examsDAL.deleteExam(examId);
    }

    public List<ExamsDTO> searchExam(String keyword) {
        return examsDAL.searchExam(keyword);
    }
    
    public List<String> getAllTestCodes() {
        List<String> testCodes = new ArrayList<>();
        String sql = "SELECT testCode FROM test";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                testCodes.add(rs.getString("testCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testCodes;
    }
    
    public List<String> getExamCodesByTest(String testCode) {
    List<String> exCodes = new ArrayList<>();
    String sql = "SELECT exCode FROM exams WHERE testCode = ?";

    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, testCode);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                exCodes.add(rs.getString("exCode"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exCodes;
}
    
    public List<QuestionsDTO> getQuestionsByExam(String exCode) {
        List<QuestionsDTO> questions = new ArrayList<>();
        String sql = "SELECT q.qID, q.qContent, q.qPictures, q.qLevel " +
                     "FROM questions q " +
                     "JOIN exams e ON FIND_IN_SET(q.qID, e.ex_quesIDs) " +
                     "WHERE e.exCode = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, exCode);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    QuestionsDTO question = new QuestionsDTO(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getString("qLevel")
                    );
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public List<AnswersDTO> getAnswersByQuestion(int qID) {
            List<AnswersDTO> answers = new ArrayList<>();
            String sql = "SELECT awID, qID, awContent, awPictures, isRight FROM answers WHERE qID = ?";

            try (Connection conn = MySQLConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, qID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        AnswersDTO answer = new AnswersDTO(
                            rs.getInt("awID"),
                            rs.getInt("qID"),
                            rs.getString("awContent"),
                            rs.getString("awPictures"),
                            rs.getBoolean("isRight")
                        );
                        answers.add(answer);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return answers;
    }
    
    public List<ExamsDTO> getExamsByTestCode(String testCode) {
        return examsDAL.getExamsByTestCode(testCode);
    }

    
}
