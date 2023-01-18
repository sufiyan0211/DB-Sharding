package com.learn.sharding.controller;

import com.learn.sharding.student.entity.Student;
import com.learn.sharding.teacher.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    List<Student> students;
    List<Teacher> teachers;
}
