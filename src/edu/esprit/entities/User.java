/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author 
 */
public class User {
    int idUser,phone;
    String fname,lname,mail,uname,pwd,role,lang1,lang2,lang3,city,nationality,lang,deb,end,dispo,relation;

    public User() {
    }

    public User(int idUser, String fname, String lname, String mail, int phone, String uname, String pwd, String role, String lang1, String lang2, String lang3, String city, String nationality, String lang, String deb, String end, String dispo, String relation) {
        this.idUser = idUser;
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.phone = phone;
        this.uname = uname;
        this.pwd = pwd;
        this.role = role;
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.lang3 = lang3;
        this.city = city;
        this.nationality = nationality;
        this.lang = lang;
        this.deb = deb;
        this.end = end;
        this.dispo = dispo;
        this.relation = relation;
    }
    
    public User(String fname, String lname, String mail, int phone, String uname, String role) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.phone = phone;
        this.uname = uname;
        this.role = role;
    }

    public User(String mail, String pwd) {
        this.mail = mail;
        this.pwd = pwd;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLang1() {
        return lang1;
    }

    public void setLang1(String lang1) {
        this.lang1 = lang1;
    }

    public String getLang2() {
        return lang2;
    }

    public void setLang2(String lang2) {
        this.lang2 = lang2;
    }

    public String getLang3() {
        return lang3;
    }

    public void setLang3(String lang3) {
        this.lang3 = lang3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDeb() {
        return deb;
    }

    public void setDeb(String deb) {
        this.deb = deb;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "User{" + "fname=" + fname + ", lname=" + lname + ", mail=" + mail + ", phone=" + phone + ", uname=" + uname + ", role=" + role + ", lang1=" + lang1 + ", lang2=" + lang2 + ", lang3=" + lang3 + ", city=" + city + ", nationality=" + nationality + ", lang=" + lang + ", deb=" + deb + ", end=" + end + ", dispo=" + dispo + ", relation=" + relation + '}';
    }
    
}
