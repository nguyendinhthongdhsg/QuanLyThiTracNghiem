/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.util.List;
public class QuestionsDTO {
    private int qID;
    private String qContent;
    private String qPictures;
    private int qTopicID;
    private String qLevel;
    private byte qStatus;
    private List<AnswersDTO> answers;
    public QuestionsDTO() {}
    public QuestionsDTO(int qID, String qContent, String qPictures, int qTopicID, String qLevel, byte qStatus, List<AnswersDTO> answers) {
        this.qID = qID;
        this.qContent = qContent;
        this.qPictures = qPictures;
        this.qTopicID = qTopicID;
        this.qLevel = qLevel;
        this.qStatus = qStatus;
        this.answers = answers;
    }
    
    public QuestionsDTO(int qID, String qContent, String qPictures, String qLevel) {
    this.qID = qID;
    this.qContent = qContent;
    this.qPictures = qPictures;
    this.qLevel = qLevel;
}

    // Getters and setters
    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public String getqPictures() {
        return qPictures;
    }

    public void setqPictures(String qPictures) {
        this.qPictures = qPictures;
    }

    public int getqTopicID() {
        return qTopicID;
    }

    public void setqTopicID(int qTopicID) {
        this.qTopicID = qTopicID;
    }

    public String getqLevel() {
        return qLevel;
    }

    public void setqLevel(String qLevel) {
        this.qLevel = qLevel;
    }

    public byte getqStatus() {
        return qStatus;
    }

    public void setqStatus(byte qStatus) {
        this.qStatus = qStatus;
    }
    public List<AnswersDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersDTO> answers) {
        this.answers = answers;
    }
}

