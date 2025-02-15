package BLL;

import DAL.ExamsDAL;
import DTO.ExamsDTO;
import java.util.List;

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
}
