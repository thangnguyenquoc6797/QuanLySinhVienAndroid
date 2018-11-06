package com.nguyenquocthang.quanlysv;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String gendet;
    private String DOB;

    public Student(int id, String name, String gendet, String DOB) {
        this.id = id;
        this.name = name;
        this.gendet = gendet;
        this.DOB = DOB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGendet() {
        return gendet;
    }

    public void setGendet(String gendet) {
        this.gendet = gendet;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
