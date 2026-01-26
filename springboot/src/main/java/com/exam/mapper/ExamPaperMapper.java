package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamPaper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 试卷Mapper接口
 * 用于试卷基本信息的数据访问操作
 */
@Mapper
public interface ExamPaperMapper {

    /**
     * 分页查询所有试卷
     * @param page 分页对象
     * @return 分页结果
     */
    @Select("select * from exam_paper")
    IPage<ExamPaper> findAll(Page<ExamPaper> page);

    /**
     * 根据ID查询试卷
     * @param paperId 试卷ID
     * @return 试卷对象
     */
    @Select("select * from exam_paper where paperId = #{paperId}")
    ExamPaper findById(Integer paperId);

    /**
     * 新增试卷
     * @param examPaper 试卷对象
     * @return 新增结果
     */
    @Options(useGeneratedKeys = true, keyProperty = "paperId")
    @Insert("insert into exam_paper(paperName, totalScore, examDuration, createTime) values (#{paperName}, #{totalScore}, #{examDuration}, #{createTime})")
    int add(ExamPaper examPaper);

    /**
     * 修改试卷
     * @param examPaper 试卷对象
     * @return 修改结果
     */
    @Update("update exam_paper set paperName = #{paperName}, totalScore = #{totalScore}, examDuration = #{examDuration} where paperId = #{paperId}")
    int update(ExamPaper examPaper);

    /**
     * 删除试卷
     * @param paperId 试卷ID
     * @return 删除结果
     */
    @Delete("delete from exam_paper where paperId = #{paperId}")
    int delete(Integer paperId);

    /**
     * 查询最后一条记录的paperId
     * @return 试卷ID
     */
    @Select("select paperId from exam_paper order by paperId desc limit 1")
    Integer findOnlyPaperId();
}
