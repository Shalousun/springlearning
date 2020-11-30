package com.sunyu.openapi.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author yu 2020/11/28.
 */
@Data
public class Ball {
    /**
     * 主键
     */
    private long id;

    /**
     * 种类
     */
    @NotBlank
    @Size(max = 30)
    private String category;

    /**
     * 所属人
     */
    @Size(max = 25)
    private String owner;

    /**
     * 几人一组
     */
    @Max(50)
    private Integer players;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }
}
