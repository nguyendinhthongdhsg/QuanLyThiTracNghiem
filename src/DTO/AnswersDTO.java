/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class AnswersDTO {
    private int awID;
    private int qID;
    private String awContent;
    private String awPictures;
    private boolean isRight;
    private byte awStatus;

    public AnswersDTO() {}

    // Getters and setters
    public int getAwID() {
        return awID;
    }

    public void setAwID(int awID) {
        this.awID = awID;
    }

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public String getAwContent() {
        return awContent;
    }

    public void setAwContent(String awContent) {
        this.awContent = awContent;
    }

    public String getAwPictures() {
        return awPictures;
    }

    public void setAwPictures(String awPictures) {
        this.awPictures = awPictures;
    }

    public boolean isIsRight() {
        return isRight;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public byte getAwStatus() {
        return awStatus;
    }

    public void setAwStatus(byte awStatus) {
        this.awStatus = awStatus;
    }
}

