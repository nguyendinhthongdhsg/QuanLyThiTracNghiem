/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class QuestionsDTO {
    private int qID;
    private String qContent;
    private String qPictures;
    private int qTopicID;
    private String qLevel;
    private byte qStatus;

    public QuestionsDTO() {}

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
}

