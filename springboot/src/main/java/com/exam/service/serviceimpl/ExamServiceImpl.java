package com.exam.service.serviceimpl;

import com.exam.entity.ExamAnswer;
import com.exam.entity.ExamRecord;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.mapper.ExamAnswerMapper;
import com.exam.mapper.ExamRecordMapper;
import com.exam.service.ExamService;
import com.exam.service.FillQuestionService;
import com.exam.service.JudgeQuestionService;
import com.exam.service.MultiQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 在线考试服务实现类
 * 用于实现在线考试和自动判分的核心业务逻辑
 */
@Service
public class ExamServiceImpl implements ExamService {

    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);

    @Autowired
    private ExamRecordMapper examRecordMapper;

    @Autowired
    private ExamAnswerMapper examAnswerMapper;

    @Autowired
    private MultiQuestionService multiQuestionService;

    @Autowired
    private FillQuestionService fillQuestionService;

    @Autowired
    private JudgeQuestionService judgeQuestionService;

    /**
     * 开始考试
     * @param paperId 试卷ID
     * @param userId 考生ID
     * @return 考试记录ID
     */
    @Override
    public Integer startExam(Integer paperId, Integer userId) {
        logger.info("考试开始：用户ID={}, 试卷ID={}", userId, paperId);
        // 创建考试记录
        ExamRecord examRecord = new ExamRecord();
        examRecord.setPaperId(paperId);
        examRecord.setUserId(userId);
        examRecord.setStartTime(new Date());
        examRecord.setEndTime(null);
        examRecord.setTotalScore(0);

        // 保存考试记录
        examRecordMapper.add(examRecord);
        logger.info("考试记录创建成功：记录ID={}, 用户ID={}, 试卷ID={}", examRecord.getRecordId(), userId, paperId);
        return examRecord.getRecordId();
    }

    /**
     * 提交试卷并自动判分
     * @param recordId 考试记录ID
     * @param answers 考生答题列表
     * @return 考试总分
     */
    @Override
    public Integer submitExam(Integer recordId, List<ExamAnswer> answers) {
        logger.info("试卷提交：记录ID={}, 答题数量={}", recordId, answers != null ? answers.size() : 0);
        // 判分前准备
        int totalScore = 0;

        // 遍历所有答题记录，进行自动判分
        if (answers != null && !answers.isEmpty()) {
            for (ExamAnswer answer : answers) {
                // 判分逻辑
                int score = autoJudge(answer);
                answer.setScore(score);
                answer.setRecordId(recordId);

                // 保存答题记录
                examAnswerMapper.add(answer);

                // 累加总分
                totalScore += score;
            }
        }

        // 更新考试记录
        ExamRecord examRecord = examRecordMapper.findById(recordId);
        if (examRecord != null) {
            examRecord.setEndTime(new Date());
            examRecord.setTotalScore(totalScore);
            examRecordMapper.update(examRecord);
            logger.info("自动判分完成：记录ID={}, 总分={}, 用户ID={}, 试卷ID={}", recordId, totalScore, examRecord.getUserId(), examRecord.getPaperId());
        }

        return totalScore;
    }

    /**
     * 获取考试结果
     * @param recordId 考试记录ID
     * @return 考试结果，包含考试记录和答题详情
     */
    @Override
    public Map<String, Object> getExamResult(Integer recordId) {
        Map<String, Object> result = new HashMap<>();

        // 获取考试记录
        ExamRecord examRecord = examRecordMapper.findById(recordId);
        result.put("examRecord", examRecord);

        // 获取答题详情
        List<ExamAnswer> answers = examAnswerMapper.findByRecordId(recordId);
        result.put("answers", answers);

        return result;
    }

    /**
     * 查询用户的考试成绩
     * @param userId 用户ID
     * @return 考试记录列表
     */
    @Override
    public List<ExamRecord> getScoresByUserId(Integer userId) {
        return examRecordMapper.findByUserId(userId);
    }

    /**
     * 查询试卷的所有考试成绩
     * @param paperId 试卷ID
     * @return 考试记录列表
     */
    @Override
    public List<ExamRecord> getScoresByPaperId(Integer paperId) {
        return examRecordMapper.findByPaperId(paperId);
    }

    /**
     * 统计考试成绩（平均分、最高分、最低分）
     * @param paperId 试卷ID
     * @return 统计结果，包含平均分、最高分、最低分和参考人数
     */
    @Override
    public Map<String, Object> statisticScore(Integer paperId) {
        logger.info("开始统计考试成绩：试卷ID={}", paperId);
        Map<String, Object> result = examRecordMapper.statisticScore(paperId);
        logger.info("成绩统计完成：试卷ID={}, 统计结果={}", paperId, result);
        return result;
    }

    /**
     * 自动判分
     * @param answer 考生答题记录
     * @return 该题得分
     */
    private int autoJudge(ExamAnswer answer) {
        int questionType = answer.getQuestionType();
        Integer questionId = answer.getQuestionId();
        String userAnswer = answer.getUserAnswer();

        // 根据题目类型获取正确答案
        String correctAnswer = null;
        int questionScore = 0;

        switch (questionType) {
            case 1: // 选择题
                MultiQuestion multiQuestion = multiQuestionService.findById(questionId);
                if (multiQuestion != null) {
                    correctAnswer = multiQuestion.getRightAnswer();
                    questionScore = multiQuestion.getScore();
                }
                break;
            case 2: // 填空题
                FillQuestion fillQuestion = fillQuestionService.findById(questionId);
                if (fillQuestion != null) {
                    correctAnswer = fillQuestion.getAnswer();
                    questionScore = fillQuestion.getScore();
                }
                break;
            case 3: // 判断题
                JudgeQuestion judgeQuestion = judgeQuestionService.findById(questionId);
                if (judgeQuestion != null) {
                    correctAnswer = judgeQuestion.getAnswer();
                    questionScore = judgeQuestion.getScore();
                }
                break;
            default:
                return 0;
        }

        // 比较答案，计算得分
        if (correctAnswer != null && correctAnswer.equals(userAnswer)) {
            return questionScore;
        }
        return 0;
    }
}
