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
    boolean isQuestionAdded = questionsDAL.addQuestion(question);  
    if (isQuestionAdded) {
        for (AnswersDTO answer : question.getAnswers()) {
            boolean isAnswerAdded = answersDAL.addAnswer(answer, question.getqID());
            if (!isAnswerAdded) {
                return false; 
            }
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
