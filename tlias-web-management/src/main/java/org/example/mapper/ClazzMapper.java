package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    int deleteById(Integer id);

    void Insert(Clazz clazz);
    
    // 根据名称查询班级
    List<Clazz> getByName(String name);

    Clazz getById(Integer id);
    
    void update(Clazz clazz);

    List<Clazz> findAll();
}