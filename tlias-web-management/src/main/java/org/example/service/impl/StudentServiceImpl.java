package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public PageResult page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> studentList = studentMapper.list(studentQueryParam);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult(p.getTotal(), studentList);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);

    }

    @Override
    public void save(Student student){
        // 数据校验
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空");
        }
        if (student.getNo() == null || student.getNo().isEmpty()) {
            throw new IllegalArgumentException("学号不能为空");
        }
        if (student.getGender() == null || student.getGender().isEmpty()) {
            throw new IllegalArgumentException("性别不能为空");
        }
        if (student.getPhone() == null || student.getPhone().isEmpty()) {
            throw new IllegalArgumentException("手机号不能为空");
        }
        if (student.getDegree() == null) {
            throw new IllegalArgumentException("学历不能为空");
        }
        if (student.getClazzId() == null) {
            throw new IllegalArgumentException("班级ID不能为空");
        }
        
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getById(Integer id){
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student){
        // 数据校验
        if (student.getId() == null) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空");
        }
        if (student.getNo() == null || student.getNo().isEmpty()) {
            throw new IllegalArgumentException("学号不能为空");
        }
        if (student.getGender() == null || student.getGender().isEmpty()) {
            throw new IllegalArgumentException("性别不能为空");
        }
        if (student.getPhone() == null || student.getPhone().isEmpty()) {
            throw new IllegalArgumentException("手机号不能为空");
        }
        if (student.getDegree() == null) {
            throw new IllegalArgumentException("学历不能为空");
        }
        if (student.getClazzId() == null) {
            throw new IllegalArgumentException("班级ID不能为空");
        }
        
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void updateViolation(Integer id, Integer score){
        studentMapper.updateViolation(id, score, LocalDateTime.now());
    }
}