package com.flyingideal.entity;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @author yanchao
 * @date 2018/3/16 14:45
 */
public class UserMaster implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String location;

    public UserMaster() {}

    public UserMaster(@Param("id") Integer id, @Param("email") String email) {
        this.id = id;
        this.email = email;
    }

    public UserMaster(String name, String email, String phone, String location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserMaster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
