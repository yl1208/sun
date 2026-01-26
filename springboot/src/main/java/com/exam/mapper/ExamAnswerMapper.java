package com.exam.mapper;

import com.exam.entity.ExamAnswer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 考生答题映射器
 * 用于考生答题记录的数据访问操作
 */
@Mapper
public interface ExamAnswerMapper {

    /**
     * 新增考生答题记录
     * @param examAnswer 考生答题对象
     * @return 新增结果
     */
    @Insert("insert into exam_answer(recordId, questionId, questionType, userAnswer, score) values (#{recordId}, #{questionId}, #{questionType}, #{userAnswer}, #{score})")
    int add(ExamAnswer examAnswer);

    /**
     * 根据考试记录ID查询所有答题记录
     * @param recordId 考试记录ID
     * @return 答题记录列表
     */
    @Select("select * from exam_answer where recordId = #{recordId}")
    List<ExamAnswer> findByRecordId(Integer recordId);
}
