package com.andreibarroso.apidebezium.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public  class Student {
    @Id
    private Integer id;

    private String name;

    private String address;

    private String email;
}
