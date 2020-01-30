package com.chaohappy.login;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 权限 */
    private String authority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}