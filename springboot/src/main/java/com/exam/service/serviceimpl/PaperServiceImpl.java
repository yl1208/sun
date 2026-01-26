package com.exam.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;
import com.exam.mapper.PaperMapper;
import com.exam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    /**
     * 查询所有试卷-题目关联
     * @return 关联列表
     */
    @Override
    public List<PaperManage> findAll() {
        return paperMapper.findAll();
    }

    /**
     * 根据试卷ID查询关联的题目
     * @param paperId 试卷ID
     * @return 关联列表
     */
    @Override
    public List<PaperManage> findById(Integer paperId) {
        return paperMapper.findById(paperId);
    }

    /**
     * 添加试卷-题目关联
     * @param paperManage 关联对象
     * @return 添加结果
     */
    @Override
    public int add(PaperManage paperManage) {
        return paperMapper.add(paperManage);
    }

    /**
     * 根据试卷ID删除所有关联的题目
     * @param paperId 试卷ID
     * @return 删除结果
     */
    @Override
    public int deleteByPaperId(Integer paperId) {
        return paperMapper.deleteByPaperId(paperId);
    }

    /**
     * 根据试卷ID和题目类型删除关联的题目
     * @param paperId 试卷ID
     * @param questionType 题目类型
     * @return 删除结果
     */
    @Override
    public int deleteByPaperIdAndType(Integer paperId, Integer questionType) {
        return paperMapper.deleteByPaperIdAndType(paperId, questionType);
    }

    /**
     * 批量添加试卷-题目关联
     * @param paperManages 关联列表
     * @return 添加结果
     */
    @Override
    public int batchAdd(List<PaperManage> paperManages) {
        // 由于没有XML配置，这里使用循环添加的方式实现批量添加
        int count = 0;
        for (PaperManage paperManage : paperManages) {
            count += paperMapper.add(paperManage);
        }
        return count;
    }
}
