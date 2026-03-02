package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级管理控制器
 * 分页查询接口返回数据格式:
 * {
 *   "code": 1,           // 响应码, 1 成功 , 0 失败
 *   "msg": "success",    // 提示信息
 *   "data": {            // 返回的数据
 *     "total": 100,      // 总记录数
 *     "rows": [          // 数据列表
 *       {
 *         "id": 1,              // id
 *         "name": "JavaEE-1",   // 班级名称
 *         "room": "A101",       // 班级教室
 *         "beginDate": "2023-09-01",  // 开课时间
 *         "endDate": "2024-03-01",    // 结课时间
 *         "masterId": 1,              // 班主任(员工ID)
 *         "masterName": "张三",       // 班主任姓名(员工姓名)
 *         "createTime": "2023-08-01 10:00:00",  // 创建时间
 *         "updateTime": "2023-08-01 10:00:00",  // 更新时间
 *         "status": "已开班"          // 状态 （未开班、已开班、已结课）
 *       }
 *     ]
 *   }
 * }
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询班级信息
     * @param clazzQueryParam 查询参数（包含分页参数、班级名称、开课和结课时间范围）
     * @return 班级分页查询结果
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询请求参数： {}", clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据ID删除班级
     * @param id 班级ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级, id: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }


    /**
     * 新增班级
     * @param clazz 新增的班级信息
     * @return 新增结果
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("新增班级, clazz: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 根据id 查询班级
     * @param id
     * @return 修改结果
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询班级, id: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
    
    /**
     * 更新班级信息
     * @param id 班级ID
     * @param clazz 更新的班级信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Clazz clazz) {
        log.info("更新班级, id: {}, clazz: {}", id, clazz);
        clazz.setId(id);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }
}