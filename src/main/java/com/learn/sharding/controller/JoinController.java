package com.learn.sharding.controller;

import com.learn.sharding.student.repository.StudentRepository;
import com.learn.sharding.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/")
    public Response getResponse() {
        Response response = new Response();

        response.setStudents(studentRepository.findAll());
        response.setTeachers(teacherRepository.findAll());

        return response;
    }
}
