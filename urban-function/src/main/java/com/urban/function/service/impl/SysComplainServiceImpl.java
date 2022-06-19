package com.urban.function.service.impl;

import java.util.List;
import com.urban.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.urban.function.mapper.SysComplainMapper;
import com.urban.function.domain.SysComplain;
import com.urban.function.service.ISysComplainService;

/**
 * 投诉举报Service业务层处理
 * 
 * @author urban
 * @date 2022-05-12
 */
@Service
public class SysComplainServiceImpl implements ISysComplainService 
{
    @Autowired
    private SysComplainMapper sysComplainMapper;

    /**
     * 查询投诉举报
     * 
     * @param complainId 投诉举报主键
     * @return 投诉举报
     */
    @Override
    public SysComplain selectSysComplainByComplainId(Integer complainId)
    {
        return sysComplainMapper.selectSysComplainByComplainId(complainId);
    }

    /**
     * 查询投诉举报列表
     * 
     * @param sysComplain 投诉举报
     * @return 投诉举报
     */
    @Override
    public List<SysComplain> selectSysComplainList(SysComplain sysComplain)
    {
        return sysComplainMapper.selectSysComplainList(sysComplain);
    }

    /**
     * 新增投诉举报
     * 
     * @param sysComplain 投诉举报
     * @return 结果
     */
    @Override
    public int insertSysComplain(SysComplain sysComplain)
    {
        sysComplain.setCreateTime(DateUtils.getNowDate());
        return sysComplainMapper.insertSysComplain(sysComplain);
    }

    /**
     * 修改投诉举报
     * 
     * @param sysComplain 投诉举报
     * @return 结果
     */
    @Override
    public int updateSysComplain(SysComplain sysComplain)
    {
        sysComplain.setUpdateTime(DateUtils.getNowDate());
        return sysComplainMapper.updateSysComplain(sysComplain);
    }

    /**
     * 批量删除投诉举报
     * 
     * @param complainIds 需要删除的投诉举报主键
     * @return 结果
     */
    @Override
    public int deleteSysComplainByComplainIds(Integer[] complainIds)
    {
        return sysComplainMapper.deleteSysComplainByComplainIds(complainIds);
    }

    /**
     * 删除投诉举报信息
     * 
     * @param complainId 投诉举报主键
     * @return 结果
     */
    @Override
    public int deleteSysComplainByComplainId(Integer complainId)
    {
        return sysComplainMapper.deleteSysComplainByComplainId(complainId);
    }
}
