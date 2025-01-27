/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class TopicsDTO {
    private int tpID;
    private String tpTitle;
    private int tpParent;
    private byte tpStatus;

    public TopicsDTO() {}
    // Getters and setters
    public int getTpID() {
        return tpID;
    }

    public void setTpID(int tpID) {
        this.tpID = tpID;
    }

    public String getTpTitle() {
        return tpTitle;
    }

    public void setTpTitle(String tpTitle) {
        this.tpTitle = tpTitle;
    }

    public int getTpParent() {
        return tpParent;
    }

    public void setTpParent(int tpParent) {
        this.tpParent = tpParent;
    }

    public byte getTpStatus() {
        return tpStatus;
    }

    public void setTpStatus(byte tpStatus) {
        this.tpStatus = tpStatus;
    }
}


