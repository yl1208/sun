package com.exam.mapper;

import com.exam.entity.PaperManage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperMapper {
    /**
     * 查询所有试卷-题目关联
     * @return 关联列表
     */
    @Select("select paperId, questionType,questionId from paper_manage")
    List<PaperManage> findAll();

    /**
     * 根据试卷ID查询关联的题目
     * @param paperId 试卷ID
     * @return 关联列表
     */
    @Select("select paperId, questionType,questionId from paper_manage where paperId = #{paperId}")
    List<PaperManage> findById(Integer paperId);

    /**
     * 添加试卷-题目关联
     * @param paperManage 关联对象
     * @return 添加结果
     */
    @Insert("insert into paper_manage(paperId,questionType,questionId) values " +
            "(#{paperId},#{questionType},#{questionId})")
    int add(PaperManage paperManage);

    /**
     * 根据试卷ID删除所有关联的题目
     * @param paperId 试卷ID
     * @return 删除结果
     */
    @Delete("delete from paper_manage where paperId = #{paperId}")
    int deleteByPaperId(Integer paperId);

    /**
     * 根据试卷ID和题目类型删除关联的题目
     * @param paperId 试卷ID
     * @param questionType 题目类型
     * @return 删除结果
     */
    @Delete("delete from paper_manage where paperId = #{paperId} and questionType = #{questionType}")
    int deleteByPaperIdAndType(Integer paperId, Integer questionType);

    /**
     * 批量添加试卷-题目关联
     * @param paperManages 关联列表
     * @return 添加结果
     */
    int batchAdd(@Param("paperManages") List<PaperManage> paperManages);
}
