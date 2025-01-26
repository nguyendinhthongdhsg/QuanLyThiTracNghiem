/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class Exams {
    private String testCode;
    private int exOrder;
    private String exCode;
    private String exQuesIDs;

    public Exams() {}

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

