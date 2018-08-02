package com.ooa1769.bs.support.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public AbstractEntity() {
    }

    @JsonIgnore
    public String getFormattedCreateDate() {
        return getFormattedDate(createDate, "yyyy.MM.dd HH:mm:ss");
    }

    @JsonIgnore
    public String getFormattedModifiedDate() {
        return getFormattedDate(modifiedDate, "yyyy.MM.dd HH:mm:ss");
    }

    private String getFormattedDate(LocalDateTime dateTime, String format) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
