package com.chaohappy.demo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Spitter {

    private String fullName;

    @Size(min=3,max=20,message="用户名必须在3~20长度之间")
    @Pattern(regexp="^[a-zA-Z0-9]+$",message="用户名格式不正确")
    private String username;

    private String password;

    private String email;

    private Boolean updateByEmail;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getUpdateByEmail() {
        return updateByEmail;
    }

    public void setUpdateByEmail(Boolean updateByEmail) {
        this.updateByEmail = updateByEmail;
    }
}
