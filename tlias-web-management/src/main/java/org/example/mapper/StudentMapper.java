package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Student student);

    Student getById(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Integer score, LocalDateTime updateTime);
}