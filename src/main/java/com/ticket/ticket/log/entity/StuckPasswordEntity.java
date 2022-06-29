package com.ticket.ticket.log.entity;

import javax.persistence.*;

/**
 * @Author ZY
 * @Date 2022/6/17 20:18
 */
@Entity
@Table(name = "stuck_password" , schema = "ticket" , catalog = "")
public class StuckPasswordEntity {
    private String id;
    private String stuckPassword;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Basic
    @Column(name = "stuckPassword")
    public String getStuckPassword() {
        return stuckPassword;
    }

    public void setStuckPassword(String stuckPassword) {
        this.stuckPassword = stuckPassword;
    }

    @Override
    public String toString() {
        return "StuckPasswordEntity{" +
                "id=" + id +
                ", stuckPassword='" + stuckPassword + '\'' +
                '}';
    }
}
