package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//判断题

@Mapper
public interface JudgeQuestionMapper {

    /**
     * 根据试卷ID查询判断题
     * @param paperId 试卷ID
     * @return 判断题列表
     */
    @Select("select * from judge_question where questionId in (select questionId from paper_manage where questionType = 3 and paperId = #{paperId})")
    List<JudgeQuestion> findByIdAndType(Integer paperId);

    /**
     * 分页查询所有判断题
     * @param page 分页对象
     * @return 分页结果
     */
    @Select("select * from judge_question")
    IPage<JudgeQuestion> findAll(Page page);

    /**
     * 查询最后一条记录的questionId
     * @return JudgeQuestion
     */
    @Select("select questionId from judge_question order by questionId desc limit 1")
    JudgeQuestion findOnlyQuestionId();

    /**
     * 添加判断题
     * @param judgeQuestion 判断题对象
     * @return 添加结果
     */
    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    @Insert("insert into judge_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(JudgeQuestion judgeQuestion);

    /**
     * 随机获取指定科目和数量的判断题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 判断题ID列表
     */
    @Select("select questionId from judge_question  where subject=#{subject}  order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);

    /**
     * 根据ID查询判断题
     * @param questionId 题目ID
     * @return 判断题对象
     */
    @Select("select * from judge_question where questionId = #{questionId}")
    JudgeQuestion findById(Integer questionId);

    /**
     * 更新判断题
     * @param judgeQuestion 判断题对象
     * @return 更新结果
     */
    @Update("update judge_question set subject = #{subject}, question = #{question}, answer = #{answer}, analysis = #{analysis}, level = #{level}, section = #{section} where questionId = #{questionId}")
    int update(JudgeQuestion judgeQuestion);

    /**
     * 删除判断题
     * @param questionId 题目ID
     * @return 删除结果
     */
    @Delete("delete from judge_question where questionId = #{questionId}")
    int delete(Integer questionId);
}
