package com.example.localsqldemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author Coco
 * @ClassName BankCard
 * @Date 2020/9/4 13:26
 * @Description TODO
 */
@Entity
public class BankCard {
    @Id
    Long Id;
    String cardNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    Long bankId;

    @Generated(hash = 59780525)
    public BankCard(Long Id, String cardNum, String name, Long bankId) {
        this.Id = Id;
        this.cardNum = cardNum;
        this.name = name;
        this.bankId = bankId;
    }

    @Generated(hash = 1025789730)
    public BankCard() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
