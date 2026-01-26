package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考生答题实体类
 * 用于记录考生对每一道题目的作答情况
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAnswer {
    /**
     * 考试记录ID
     */
    private Integer recordId;

    /**
     * 题目ID
     */
    private Integer questionId;

    /**
     * 题目类型 (1:选择题, 2:填空题, 3:判断题)
     */
    private Integer questionType;

    /**
     * 考生答案
     */
    private String userAnswer;

    /**
     * 该题得分
     */
    private Integer score;
}
