package com.timor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2021-09-17 10:52:23
 */
public class AdminDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @JsonProperty("name")
    private String username;

    /**
     * 用户头像地址
     */
    private String avatar;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

