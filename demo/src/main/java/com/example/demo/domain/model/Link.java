package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@Data
@RedisHash("Link")
public class Link {

    @JsonIgnore
    @Id
    private String id;

    private String URL;

    @JsonIgnore
    @Indexed
    private Long date;

    public Link(String URL) {
        this.URL = URL;
        date = new Date().getTime();
    }
}
