package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//选择题
@Mapper
public interface MultiQuestionMapper {
    /**
     * 根据试卷ID查询选择题
     * select * from multiquestions where questionId in (
     * 	select questionId from papermanage where questionType = 1 and paperId = 1001
     * )
     */
    @Select("select * from multi_question where questionId in (select questionId from paper_manage where questionType = 1 and paperId = #{paperId})")
    List<MultiQuestion> findByIdAndType(Integer PaperId);

    /**
     * 分页查询所有选择题
     * @param page 分页对象
     * @return 分页结果
     */
    @Select("select * from multi_question")
    IPage<MultiQuestion> findAll(Page page);

    /**
     * 查询最后一条记录的questionId
     * @return MultiQuestion
     */
    @Select("select questionId from multi_question order by questionId desc limit 1")
    MultiQuestion findOnlyQuestionId();

    /**
     *  mybatis主键自增长，注解返回主键：@Options()
     * @param multiQuestion 选择题对象
     * @return 插入结果
     */
    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    @Insert("insert into multi_question(subject,question,answerA,answerB,answerC,answerD,rightAnswer,analysis,section,level) " +
            "values(#{subject},#{question},#{answerA},#{answerB},#{answerC},#{answerD},#{rightAnswer},#{analysis},#{section},#{level})")
    int add(MultiQuestion multiQuestion);

    /**
     * 注：rand()随机数
     * @param subject 科目
     * @param pageNo 数量
     * @return 随机选择题ID列表
     */
    @Select("select questionId from multi_question  where subject =#{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);

    /**
     * 根据ID查询选择题
     * @param questionId 题目ID
     * @return 选择题对象
     */
    @Select("select * from multi_question where questionId = #{questionId}")
    MultiQuestion findById(Integer questionId);

    /**
     * 更新选择题
     * @param multiQuestion 选择题对象
     * @return 更新结果
     */
    @Update("update multi_question set subject = #{subject}, question = #{question}, answerA = #{answerA}, answerB = #{answerB}, answerC = #{answerC}, answerD = #{answerD}, rightAnswer = #{rightAnswer}, analysis = #{analysis}, section = #{section}, level = #{level} where questionId = #{questionId}")
    int update(MultiQuestion multiQuestion);

    /**
     * 删除选择题
     * @param questionId 题目ID
     * @return 删除结果
     */
    @Delete("delete from multi_question where questionId = #{questionId}")
    int delete(Integer questionId);
}
