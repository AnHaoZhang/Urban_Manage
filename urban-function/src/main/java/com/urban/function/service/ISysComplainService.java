package com.urban.function.service;

import java.util.List;
import com.urban.function.domain.SysComplain;

/**
 * 投诉举报Service接口
 * 
 * @author urban
 * @date 2022-05-12
 */
public interface ISysComplainService 
{
    /**
     * 查询投诉举报
     * 
     * @param complainId 投诉举报主键
     * @return 投诉举报
     */
    public SysComplain selectSysComplainByComplainId(Integer complainId);

    /**
     * 查询投诉举报列表
     * 
     * @param sysComplain 投诉举报
     * @return 投诉举报集合
     */
    public List<SysComplain> selectSysComplainList(SysComplain sysComplain);

    /**
     * 新增投诉举报
     * 
     * @param sysComplain 投诉举报
     * @return 结果
     */
    public int insertSysComplain(SysComplain sysComplain);

    /**
     * 修改投诉举报
     * 
     * @param sysComplain 投诉举报
     * @return 结果
     */
    public int updateSysComplain(SysComplain sysComplain);

    /**
     * 批量删除投诉举报
     * 
     * @param complainIds 需要删除的投诉举报主键集合
     * @return 结果
     */
    public int deleteSysComplainByComplainIds(Integer[] complainIds);

    /**
     * 删除投诉举报信息
     * 
     * @param complainId 投诉举报主键
     * @return 结果
     */
    public int deleteSysComplainByComplainId(Integer complainId);
}
