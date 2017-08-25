package com.example.taxihelper.mvp.entity;

/**
 * Created by 张兴锐 on 2017/8/25.
 */

public class UserInfo {
    

    private String accountBalance;
    private String companyAccountBalance;
    private int companyPayType;
    private int companyQuotaType;
    private String companyRemainAmount;
    private int payType;
    private String email;
    private String id;
    private String name;
    private String phone;
    private String picture;
    private int sex;
    private String uninvoiceAmount;

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCompanyAccountBalance() {
        return companyAccountBalance;
    }

    public void setCompanyAccountBalance(String companyAccountBalance) {
        this.companyAccountBalance = companyAccountBalance;
    }

    public int getCompanyPayType() {
        return companyPayType;
    }

    public void setCompanyPayType(int companyPayType) {
        this.companyPayType = companyPayType;
    }

    public int getCompanyQuotaType() {
        return companyQuotaType;
    }

    public void setCompanyQuotaType(int companyQuotaType) {
        this.companyQuotaType = companyQuotaType;
    }

    public String getCompanyRemainAmount() {
        return companyRemainAmount;
    }

    public void setCompanyRemainAmount(String companyRemainAmount) {
        this.companyRemainAmount = companyRemainAmount;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUninvoiceAmount() {
        return uninvoiceAmount;
    }

    public void setUninvoiceAmount(String uninvoiceAmount) {
        this.uninvoiceAmount = uninvoiceAmount;
    }
}
