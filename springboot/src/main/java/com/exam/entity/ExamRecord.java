package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 考试记录实体类
 * 用于记录一次完整的考试过程
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamRecord {
    /**
     * 考试记录ID
     */
    private Integer recordId;

    /**
     * 试卷ID
     */
    private Integer paperId;

    /**
     * 考生ID
     */
    private Integer userId;

    /**
     * 考试开始时间
     */
    private Date startTime;

    /**
     * 考试结束时间
     */
    private Date endTime;

    /**
     * 考试总分
     */
    private Integer totalScore;
}
