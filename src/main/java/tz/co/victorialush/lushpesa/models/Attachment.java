package tz.co.victorialush.lushpesa.models;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.tuple.GenerationTiming;

import java.sql.Timestamp;

public class Attachment {
    private String id;
    @CurrentTimestamp(timing = GenerationTiming.ALWAYS)
    private Timestamp createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
