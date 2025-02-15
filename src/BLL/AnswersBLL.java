package BLL;

import DAL.AnswersDAL;
import DTO.AnswersDTO;
import java.util.List;

public class AnswersBLL {
    private AnswersDAL answersDAL = new AnswersDAL();
    
    
    public AnswersDTO getAnswerByID(int answerId) {
    return answersDAL.getAnswerByID(answerId);
    }

    public List<AnswersDTO> getAnswersByQuestionID(int questionID) {
        return answersDAL.getAnswersByQuestionID(questionID);
    }

    public boolean addAnswer(AnswersDTO answer, int questionID) {
        return answersDAL.addAnswer(answer, questionID);
    }

    public boolean updateAnswer(AnswersDTO answer, int questionID) {
    return answersDAL.updateAnswer(answer, questionID);
    }


    public boolean deleteAnswer(int answerID, int questionID) {
    return answersDAL.deleteAnswer(answerID, questionID);
}

}
