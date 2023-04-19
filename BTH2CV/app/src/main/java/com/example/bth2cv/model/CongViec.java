package com.example.bth2cv.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id;
    private String ma,ten,noiDung,ngayHT,tinhTrang,congTac;

    public CongViec() {
    }

    public CongViec(int id, String ma, String ten, String noiDung, String ngayHT, String tinhTrang, String congTac) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.noiDung = noiDung;
        this.ngayHT = ngayHT;
        this.tinhTrang = tinhTrang;
        this.congTac = congTac;
    }

    public CongViec(String ma, String ten, String noiDung, String ngayHT, String tinhTrang, String congTac) {
        this.ma = ma;
        this.ten = ten;
        this.noiDung = noiDung;
        this.ngayHT = ngayHT;
        this.tinhTrang = tinhTrang;
        this.congTac = congTac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayHT() {
        return ngayHT;
    }

    public void setNgayHT(String ngayHT) {
        this.ngayHT = ngayHT;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getCongTac() {
        return congTac;
    }

    public void setCongTac(String congTac) {
        this.congTac = congTac;
    }
}
