package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@JsonPropertyOrder(value={"firstname", "lastname", "lastUpdate"})
public class Actor {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long id;

    @JsonProperty("firstName")
    @Column(name = "first_name")
    private String firstname;

    @JsonProperty("lastName")
    @Column(name = "last_name")
    private String lastname;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss", timezone = "GMT+8")
    @Column(name = "last_update")
    private Date lastUpdate;
}
