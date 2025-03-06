/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ThongKeDTO {
    private String maBaiThi;
    private String tenBaiThi;
    private int soLuotThi;
    private int dat;
    private int rot;
    private Date ngaythi;
    
    public ThongKeDTO() {}

    public ThongKeDTO(String maBaiThi, String tenBaiThi, int soLuotThi, int dat, int rot, Date ngaythi) {
        this.maBaiThi = maBaiThi;
        this.tenBaiThi = tenBaiThi;
        this.soLuotThi = soLuotThi;
        this.dat = dat;
        this.rot = rot;
        this.ngaythi = ngaythi;
    }

    public String getMaBaiThi() {
        return maBaiThi;
    }

    public String getTenBaiThi() {
        return tenBaiThi;
    }

    public int getSoLuotThi() {
        return soLuotThi;
    }

    public int getDat() {
        return dat;
    }

    public int getRot() {
        return rot;
    }

    public Date getNgaythi() {
        return ngaythi;
    }

    public void setMaBaiThi(String maBaiThi) {
        this.maBaiThi = maBaiThi;
    }

    public void setTenBaiThi(String tenBaiThi) {
        this.tenBaiThi = tenBaiThi;
    }

    public void setSoLuotThi(int soLuotThi) {
        this.soLuotThi = soLuotThi;
    }

    public void setDat(int dat) {
        this.dat = dat;
    }

    public void setRot(int rot) {
        this.rot = rot;
    }

    public void setNgaythi(Date ngaythi) {
        this.ngaythi = ngaythi;
    }

    @Override
    public String toString() {
        return "ThongKeDTO{" + "maBaiThi=" + maBaiThi + ", tenBaiThi=" + tenBaiThi + ", soLuotThi=" + soLuotThi + ", dat=" + dat + ", rot=" + rot + ", ngaythi=" + ngaythi + '}';
    }
}
