package tz.co.victorialush.lushpesa.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.tuple.GenerationTiming;

import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "tokens")
@Entity

public class Token {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String token;
//    @ManyToOne(targetEntity = User.class)
    private Integer userId;
    @CurrentTimestamp(timing = GenerationTiming.ALWAYS)
    private Timestamp accessTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Timestamp accessTime) {
        this.accessTime = accessTime;
    }
}
