/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;


public class Result {
    private int rsNum;
    private int userID;
    private String exCode;
    private String rsAnswers;
    private double rsMark;
    private String rsDate;

    public Result() {}

    public int getRsNum() {
        return rsNum;
    }

    public void setRsNum(int rsNum) {
        this.rsNum = rsNum;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getRsAnswers() {
        return rsAnswers;
    }

    public void setRsAnswers(String rsAnswers) {
        this.rsAnswers = rsAnswers;
    }

    public double getRsMark() {
        return rsMark;
    }

    public void setRsMark(double rsMark) {
        this.rsMark = rsMark;
    }

    public String getRsDate() {
        return rsDate;
    }

    public void setRsDate(String rsDate) {
        this.rsDate = rsDate;
    }
}

