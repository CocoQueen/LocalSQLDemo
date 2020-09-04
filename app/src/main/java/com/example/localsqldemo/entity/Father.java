package com.example.localsqldemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author Coco
 * @ClassName Father
 * @Date 2020/9/3 18:00
 * @Description TODO
 */
@Entity
public class Father {
    @Id
    Long id;
    String name;
    String age;

    @Generated(hash = 265694205)
    public Father(Long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 383274692)
    public Father() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
