package com.learn.sharding.student.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    long id;

    @Column(name = "Name")
    String name;
}
