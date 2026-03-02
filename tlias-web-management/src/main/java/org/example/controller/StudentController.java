package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("查询请求参数： {}", studentQueryParam);
        PageResult pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 删除学生
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除请求参数： {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 添加学生
     */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加请求参数： {}", student);
        studentService.save(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询id： {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }
    
    /**
     * 更新学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新请求参数：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("更新学生违纪信息, id: {}, score: {}", id, score);
        studentService.updateViolation(id, score);
        return Result.success();
    }

}