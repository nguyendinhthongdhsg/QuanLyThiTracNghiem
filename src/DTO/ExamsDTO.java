package DTO;

public class ExamsDTO {
    private int examId;  
    private String testCode;
    private String exOrder;
    private String exCode;
    private String Ex_quesIDs;

    public ExamsDTO() {}

    public ExamsDTO(int examId, String testCode, String exOrder, String exCode, String exQuestIDs) {
        this.examId = examId;
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.Ex_quesIDs = exQuestIDs;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getExOrder() {
        return exOrder;
    }

    public void setExOrder(String exOrder) {
        this.exOrder = exOrder;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getExQuestIDs() {
        return Ex_quesIDs;
    }

    public void setExQuestIDs(String exQuestIDs) {
        this.Ex_quesIDs = exQuestIDs;
    }
    
    
}
