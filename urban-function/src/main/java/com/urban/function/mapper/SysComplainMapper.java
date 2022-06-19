package com.urban.function.mapper;

import java.util.List;
import com.urban.function.domain.SysComplain;
import org.springframework.stereotype.Repository;

/**
 * 投诉举报Mapper接口
 *
 * @author urban
 * @date 2022-05-12
 */
@Repository
public interface SysComplainMapper
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
     * 删除投诉举报
     *
     * @param complainId 投诉举报主键
     * @return 结果
     */
    public int deleteSysComplainByComplainId(Integer complainId);

    /**
     * 批量删除投诉举报
     *
     * @param complainIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysComplainByComplainIds(Integer[] complainIds);
}
