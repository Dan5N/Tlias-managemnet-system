package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;


    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        // 设置默认值，防止空指针异常
        int page = (clazzQueryParam.getPage() != null) ? clazzQueryParam.getPage() : 1;
        int pageSize = (clazzQueryParam.getPageSize() != null) ? clazzQueryParam.getPageSize() : 10;
        
        PageHelper.startPage(page, pageSize);
        // 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        //3. 封装分页结果
        Page<Clazz> p = (Page<Clazz>)clazzList;
        return new PageResult(p.getTotal(), p.getResult());

    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Transactional
    @Override
    public void save(Clazz clazz) {
        // 检查必填字段
        if (clazz.getName() == null || clazz.getName().isEmpty()) {
            throw new IllegalArgumentException("班级名称不能为空");
        }
        if (clazz.getRoom() == null || clazz.getRoom().isEmpty()) {
            throw new IllegalArgumentException("班级教室不能为空");
        }
        if (clazz.getBeginDate() == null) {
            throw new IllegalArgumentException("开课时间不能为空");
        }
        if (clazz.getEndDate() == null) {
            throw new IllegalArgumentException("结课时间不能为空");
        }
        if (clazz.getSubject() == null) {
            throw new IllegalArgumentException("学科不能为空");
        }
        
        // 检查班级名称是否已存在
        List<Clazz> existingClazzList = clazzMapper.getByName(clazz.getName());
        if (!existingClazzList.isEmpty()) {
            throw new IllegalArgumentException("班级名称已存在");
        }
        
        // 检查日期逻辑
        if (clazz.getBeginDate().isAfter(clazz.getEndDate())) {
            throw new IllegalArgumentException("开课时间不能晚于结课时间");
        }
        
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.Insert(clazz);

    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);

    }
    
    @Transactional
    @Override
    public void update(Clazz clazz) {
        // 检查必填字段
        if (clazz.getName() == null || clazz.getName().isEmpty()) {
            throw new IllegalArgumentException("班级名称不能为空");
        }
        if (clazz.getRoom() == null || clazz.getRoom().isEmpty()) {
            throw new IllegalArgumentException("班级教室不能为空");
        }
        if (clazz.getBeginDate() == null) {
            throw new IllegalArgumentException("开课时间不能为空");
        }
        if (clazz.getEndDate() == null) {
            throw new IllegalArgumentException("结课时间不能为空");
        }
        
        // 检查日期逻辑
        if (clazz.getBeginDate().isAfter(clazz.getEndDate())) {
            throw new IllegalArgumentException("开课时间不能晚于结课时间");
        }
        
        clazz.setUpdateTime(LocalDateTime.now());
        
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}