package com.learn.sharding.teacher.repository;

import com.learn.sharding.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
