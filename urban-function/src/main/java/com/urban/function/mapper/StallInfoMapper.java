package com.urban.function.mapper;

import java.util.List;
import com.urban.function.domain.StallInfo;
import org.springframework.stereotype.Repository;

/**
 * 摊位信息Mapper接口
 *
 * @author urban
 * @date 2022-05-12
 */
@Repository
public interface StallInfoMapper
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
     * 新增摊位信息
     *
     * @param stallInfo 摊位信息
     * @return 结果
     */
    public int insertStallInfo(StallInfo stallInfo);

    /**
     * 获取经纬度
     *
     * @param
     */
    public List<StallInfo> getStalladdressInfoList(StallInfo stallInfo);
    /**
     * 修改摊位信息
     *
     * @param stallInfo 摊位信息
     * @return 结果
     */
    public int updateStallInfo(StallInfo stallInfo);

    /**
     * 删除摊位信息
     *
     * @param stallsId 摊位信息主键
     * @return 结果
     */
    public int deleteStallInfoByStallsId(Long stallsId);

    /**
     * 批量删除摊位信息
     *
     * @param stallsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStallInfoByStallsIds(Long[] stallsIds);
}
