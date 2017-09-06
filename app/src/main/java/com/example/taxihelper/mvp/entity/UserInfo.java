package com.example.taxihelper.mvp.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by 张兴锐 on 2017/8/25.
 */
@Entity
public class UserInfo {
   @Id
    private Long _id;
    @Unique
    private String accountBalance;
    @Unique
    private String companyAccountBalance;
    @Unique
    private int companyPayType;
    @Unique
    private int companyQuotaType;
    @Unique
    private String companyRemainAmount;
    @Unique
    private int payType;
    @Unique
    private String email;
    @Unique
    private String id;
    @Unique
    private String name;
    @Unique
    private String phone;
    @Unique
    private String picture;
    @Unique
    private int sex;
    @Unique
    private String uninvoiceAmount;

    @Generated(hash = 2123167421)
    public UserInfo(Long _id, String accountBalance, String companyAccountBalance,
            int companyPayType, int companyQuotaType, String companyRemainAmount,
            int payType, String email, String id, String name, String phone,
            String picture, int sex, String uninvoiceAmount) {
        this._id = _id;
        this.accountBalance = accountBalance;
        this.companyAccountBalance = companyAccountBalance;
        this.companyPayType = companyPayType;
        this.companyQuotaType = companyQuotaType;
        this.companyRemainAmount = companyRemainAmount;
        this.payType = payType;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.picture = picture;
        this.sex = sex;
        this.uninvoiceAmount = uninvoiceAmount;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

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

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
