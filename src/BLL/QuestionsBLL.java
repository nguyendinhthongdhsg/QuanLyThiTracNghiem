package BLL;

import DAL.QuestionsDAL;
import DAL.AnswersDAL;
import DTO.QuestionsDTO;
import DTO.AnswersDTO;
import java.util.List;

public class QuestionsBLL {
    private QuestionsDAL questionsDAL = new QuestionsDAL();
    private AnswersDAL answersDAL = new AnswersDAL();

    public boolean addQuestion(QuestionsDTO question) {
        int questionID = questionsDAL.addQuestion(question);
        if (questionID > 0) {
            for (AnswersDTO answer : question.getAnswers()) {
                answersDAL.addAnswer(answer, questionID);
            }
            return true;
        }
        return false;
    }
    
    public List<QuestionsDTO> getAllQuestions() {
        return questionsDAL.getAllQuestions();
    }
    
    public QuestionsDTO getQuestionById(int id) {
    return questionsDAL.getQuestionById(id);
    }

    
    public boolean updateQuestion(QuestionsDTO question) {
        return questionsDAL.updateQuestion(question);
    }

    public boolean deleteQuestion(int questionID) {
        return questionsDAL.deleteQuestion(questionID);
    }

    public List<QuestionsDTO> searchQuestion(String keyword) {
    return questionsDAL.searchQuestion(keyword);
    }

}
