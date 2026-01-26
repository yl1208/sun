package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 试卷实体类
 * 用于描述一套试卷的基本信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamPaper {
    /**
     * 试卷ID
     */
    private Integer paperId;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 试卷总分
     */
    private Integer totalScore;

    /**
     * 考试时长（分钟）
     */
    private Integer examDuration;

    /**
     * 创建时间
     */
    private Date createTime;
}
