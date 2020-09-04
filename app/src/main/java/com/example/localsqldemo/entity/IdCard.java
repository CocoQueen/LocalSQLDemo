package com.example.localsqldemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author Coco
 * @ClassName IdCard
 * @Date 2020/9/3 15:47
 * @Description TODO
 */
@Entity
public class IdCard {
    @Id
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    String userName;
    @Unique
    String idNo;

    @Generated(hash = 141234591)
    public IdCard(Long id, String userName, String idNo) {
        this.id = id;
        this.userName = userName;
        this.idNo = idNo;
    }

    @Generated(hash = 1500073048)
    public IdCard() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
