package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 班级实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    /**
     * ID
     */
    private Integer id; 
    
    /**
     * 班级名称
     */
    private String name; 
    
    /**
     * 班级教室
     */
    private String room; 
    
    /**
     * 开课时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate; 
    
    /**
     * 结课时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; 
    
    /**
     * 班主任ID
     */
    private Integer masterId; 
    
    /**
     * 学科
     */
    private Integer subject; 
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; 
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; 

    /**
     * 班主任姓名
     */
    private String masterName; 
    
    /**
     * 班级状态
     * 未开班 - 开课时间在当前时间之后
     * 已开班 - 开课时间在当前时间之前且结课时间在当前时间之后
     * 已结课 - 结课时间在当前时间之前
     */
    private String status; //班级状态 - 未开班 , 已开班 , 已结课
}