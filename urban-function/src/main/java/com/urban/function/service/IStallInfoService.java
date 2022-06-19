package com.urban.function.service;

import java.util.List;
import com.urban.function.domain.StallInfo;

/**
 * 摊位信息Service接口
 *
 * @author urban
 * @date 2022-05-12
 */
public interface IStallInfoService
{
    /**
     * 查询摊位信息
     *
     * @param stallsId 摊位信息主键
     * @return 摊位信息
     */
    public StallInfo selectStallInfoByStallsId(Long stallsId);

    /**
     * 查询摊位信息列表
     *
     * @param stallInfo 摊位信息
     * @return 摊位信息集合
     */
    public List<StallInfo> selectStallInfoList(StallInfo stallInfo);

    /**
     * 查询摊位信息列表
     *
     * @param stallInfo 摊位信息
     * @return 摊位信息集合
     */
    public List<StallInfo> getStalladdressInfoList(StallInfo stallInfo);

    /**
     * 新增摊位信息
     *
     * @param stallInfo 摊位信息
     * @return 结果
     */
    public int insertStallInfo(StallInfo stallInfo);

    /**
     * 修改摊位信息
     *
     * @param stallInfo 摊位信息
     * @return 结果
     */
    public int updateStallInfo(StallInfo stallInfo);

    /**
     * 批量删除摊位信息
     *
     * @param stallsIds 需要删除的摊位信息主键集合
     * @return 结果
     */
    public int deleteStallInfoByStallsIds(Long[] stallsIds);

    /**
     * 删除摊位信息信息
     *
     * @param stallsId 摊位信息主键
     * @return 结果
     */
    public int deleteStallInfoByStallsId(Long stallsId);

    /**
     * 导入用户数据
     *
     * @param stallInfoList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param stallsId 摊位编号
     * @return 结果
     */
    public String importStall(List<StallInfo> stallInfoList, Boolean isUpdateSupport, Long stallsId);

}
