/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class TestDTO {
    private int testID;
    private String testCode;
    private String testTitle;
    private int testTime;
    private int tpID;
    private int numEasy;
    private int numMedium;
    private int numDiff;
    private int test_limit;
    private String testDate;
    private int testStatus;

    public TestDTO() {}
    public TestDTO(int testID, String testCode, String testTitle, int testTime, int tpID, 
               int numEasy, int numMedium, int numDiff, int testLimit, 
               String testDate, int testStatus) {
    this.testID = testID;
    this.testCode = testCode;
    this.testTitle = testTitle;
    this.testTime = testTime;
    this.tpID = tpID;
    this.numEasy = numEasy;
    this.numMedium = numMedium;
    this.numDiff = numDiff;
    this.test_limit = testLimit;
    this.testDate = testDate;
    this.testStatus = testStatus;
}

    // Getters and setters
    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public int getTpID() {
        return tpID;
    }

    public void setTpID(int tpID) {
        this.tpID = tpID;
    }

    public int getNumEasy() {
        return numEasy;
    }

    public void setNumEasy(int numEasy) {
        this.numEasy = numEasy;
    }

    public int getNumMedium() {
        return numMedium;
    }

    public void setNumMedium(int numMedium) {
        this.numMedium = numMedium;
    }

    public int getNumDiff() {
        return numDiff;
    }

    public void setNumDiff(int numDiff) {
        this.numDiff = numDiff;
    }

    public int getTestLimit() {
        return test_limit;
    }

    public void setTestLimit(int testLimit) {
        this.test_limit = testLimit;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public int getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(int testStatus) {
        this.testStatus = testStatus;
    }
}

