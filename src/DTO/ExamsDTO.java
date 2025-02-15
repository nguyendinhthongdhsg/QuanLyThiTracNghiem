package DTO;

public class ExamsDTO {
    private int examId;  
    private String testCode;
    private int exOrder;
    private String exCode;
    private String exQuesIDs;

    public ExamsDTO() {}

    public ExamsDTO(int examId, String testCode, int exOrder, String exCode, String exQuesIDs) {
        this.examId = examId;
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.exQuesIDs = exQuesIDs;
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

    public int getExOrder() {
        return exOrder;
    }

    public void setExOrder(int exOrder) {
        this.exOrder = exOrder;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getExQuesIDs() {
        return exQuesIDs;
    }

    public void setExQuesIDs(String exQuesIDs) {
        this.exQuesIDs = exQuesIDs;
    }
}
