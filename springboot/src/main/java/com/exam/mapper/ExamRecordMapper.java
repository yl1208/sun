package com.exam.mapper;

import com.exam.entity.ExamRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 考试记录映射器
 * 用于考试记录的数据访问操作
 */
@Mapper
public interface ExamRecordMapper {

    /**
     * 新增考试记录
     * @param examRecord 考试记录对象
     * @return 新增结果
     */
    @Options(useGeneratedKeys = true, keyProperty = "recordId")
    @Insert("insert into exam_record(paperId, userId, startTime, endTime, totalScore) values (#{paperId}, #{userId}, #{startTime}, #{endTime}, #{totalScore})")
    int add(ExamRecord examRecord);

    /**
     * 根据ID查询考试记录
     * @param recordId 考试记录ID
     * @return 考试记录对象
     */
    @Select("select * from exam_record where recordId = #{recordId}")
    ExamRecord findById(Integer recordId);

    /**
     * 根据用户ID查询考试记录
     * @param userId 用户ID
     * @return 考试记录列表
     */
    @Select("select * from exam_record where userId = #{userId} order by startTime desc")
    List<ExamRecord> findByUserId(Integer userId);

    /**
     * 根据试卷ID查询考试记录
     * @param paperId 试卷ID
     * @return 考试记录列表
     */
    @Select("select * from exam_record where paperId = #{paperId} order by startTime desc")
    List<ExamRecord> findByPaperId(Integer paperId);

    /**
     * 更新考试记录的结束时间和总分
     * @param examRecord 考试记录对象
     * @return 更新结果
     */
    @Update("update exam_record set endTime = #{endTime}, totalScore = #{totalScore} where recordId = #{recordId}")
    int update(ExamRecord examRecord);

    /**
     * 统计考试成绩（平均分、最高分、最低分）
     * @param paperId 试卷ID
     * @return 统计结果，包含平均分、最高分、最低分
     */
    @Select("select avg(totalScore) as avgScore, max(totalScore) as maxScore, min(totalScore) as minScore, count(*) as totalNum from exam_record where paperId = #{paperId}")
    Map<String, Object> statisticScore(Integer paperId);
}
