package com.learn.sharding.teacher.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    long id;

    @Column(name = "Name")
    String name;

}
