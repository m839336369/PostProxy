package com.example.PostProxy.core.system.dto;

import com.example.PostProxy.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 系统用户
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseDTO {
    private static final long serialVersionUID = -7395431342743009038L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;

    private Integer enabled;
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

}