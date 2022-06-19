package com.urban.function.service.impl;

import java.util.List;

import com.urban.common.exception.ServiceException;
import com.urban.common.utils.DateUtils;
import com.urban.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.urban.function.mapper.StallInfoMapper;
import com.urban.function.domain.StallInfo;
import com.urban.function.service.IStallInfoService;

/**
 * 摊位信息Service业务层处理
 *
 * @author urban
 * @date 2022-05-13
 */
@Service
public class StallInfoServiceImpl implements IStallInfoService
{
    private static final Logger log = LoggerFactory.getLogger(StallInfoServiceImpl.class);

    @Autowired
    private StallInfoMapper stallInfoMapper;

    /**
     * 查询摊位信息
     *
     * @param stallsId 摊位信息主键
     * @return 摊位信息
     */
    @Override
    public StallInfo selectStallInfoByStallsId(Long stallsId)
    {
        return stallInfoMapper.selectStallInfoByStallsId(stallsId);
    }

    /**
     * 查询摊位信息列表
     *
     * @param stallInfo 摊位信息
     * @return 摊位信息
     */
    @Override
    public List<StallInfo> selectStallInfoList(StallInfo stallInfo)
    {
        return stallInfoMapper.selectStallInfoList(stallInfo);
    }

    /**
     * 查询摊位信息列表
     *
     * @param stallInfo 摊位信息
     * @return 摊位信息
     */
    @Override
    public List<StallInfo> getStalladdressInfoList(StallInfo stallInfo)
    {
        return stallInfoMapper.getStalladdressInfoList(stallInfo);
    }
    /**
     * 新增摊位信息
     *
     * @param stallInfo 摊位信息
     * @return 结果
     */
    @Override
    public int insertStallInfo(StallInfo stallInfo)
    {
        stallInfo.setCreateTime(DateUtils.getNowDate());
        return stallInfoMapper.insertStallInfo(stallInfo);
    }

    /**
     * 修改摊位信息
     *
     * @param stallInfo 摊位信息
     * @return 结果
     */
    @Override
    public int updateStallInfo(StallInfo stallInfo)
    {
        stallInfo.setUpdateTime(DateUtils.getNowDate());
        return stallInfoMapper.updateStallInfo(stallInfo);
    }

    /**
     * 批量删除摊位信息
     *
     * @param stallsIds 需要删除的摊位信息主键
     * @return 结果
     */
    @Override
    public int deleteStallInfoByStallsIds(Long[] stallsIds)
    {
        return stallInfoMapper.deleteStallInfoByStallsIds(stallsIds);
    }

    /**
     * 删除摊位信息信息
     *
     * @param stallsId 摊位信息主键
     * @return 结果
     */
    @Override
    public int deleteStallInfoByStallsId(Long stallsId)
    {
        return stallInfoMapper.deleteStallInfoByStallsId(stallsId);
    }

    /**
     * 导入摊位信息
     * @param stallInfoList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param stallsId 摊位编号
     * @return
     */
    @Override
    public String importStall(List<StallInfo> stallInfoList, Boolean isUpdateSupport, Long stallsId) {
        if (StringUtils.isNull(stallInfoList) || stallInfoList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<StallInfo> existList = selectStallInfoList(null);
        for (StallInfo importData : stallInfoList)
        {
            try {

                boolean userFlag = false;
                for (StallInfo entry : existList) {
                    if (entry.getStallsId().equals(importData.getStallsId())) {
                        userFlag = true;
                        break;
                    }
                }
                if (!userFlag) {
                    insertStallInfo(importData);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、数据 " + importData.getStallsId() + " 导入成功");
                } else if (isUpdateSupport) {
                    updateStallInfo(importData);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、数据 " + importData.getStallsId() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、数据 " + importData.getStallsId() + " 已存在");
                }
            }catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + importData.getStallsId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
