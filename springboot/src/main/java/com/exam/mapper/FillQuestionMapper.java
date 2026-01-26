package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//填空题
@Mapper
public interface FillQuestionMapper {

    /**
     * 根据试卷ID查询填空题
     * @param paperId 试卷ID
     * @return 填空题列表
     */
    @Select("select * from fill_question where questionId in (select questionId from paper_manage where questionType = 2 and paperId = #{paperId})")
    List<FillQuestion> findByIdAndType(Integer paperId);

    /**
     * 分页查询所有填空题
     * @param page 分页对象
     * @return 分页结果
     */
    @Select("select * from fill_question")
    IPage<FillQuestion> findAll(Page page);

    /**
     * 查询最后一条questionId
     * @return FillQuestion
     */
    @Select("select questionId from fill_question order by questionId desc limit 1")
    FillQuestion findOnlyQuestionId();

    /**
     * 添加填空题
     * @param fillQuestion 填空题对象
     * @return 添加结果
     */
    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into fill_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(FillQuestion fillQuestion);

    /**
     * 随机获取指定科目和数量的填空题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 填空题ID列表
     */
    @Select("select questionId from fill_question where subject = #{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);

    /**
     * 根据ID查询填空题
     * @param questionId 题目ID
     * @return 填空题对象
     */
    @Select("select * from fill_question where questionId = #{questionId}")
    FillQuestion findById(Integer questionId);

    /**
     * 更新填空题
     * @param fillQuestion 填空题对象
     * @return 更新结果
     */
    @Update("update fill_question set subject = #{subject}, question = #{question}, answer = #{answer}, analysis = #{analysis}, level = #{level}, section = #{section} where questionId = #{questionId}")
    int update(FillQuestion fillQuestion);

    /**
     * 删除填空题
     * @param questionId 题目ID
     * @return 删除结果
     */
    @Delete("delete from fill_question where questionId = #{questionId}")
    int delete(Integer questionId);
}
