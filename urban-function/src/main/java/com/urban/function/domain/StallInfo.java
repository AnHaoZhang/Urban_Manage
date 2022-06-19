package com.urban.function.domain;

import com.urban.common.annotation.Excel;
import com.urban.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 摊位信息对象 stall_info
 *
 * @author urban
 * @date 2022-05-12
 */
public class StallInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 摊位编号 */
    @Excel(name = "摊位编号")
    private Long stallsId;

    /** 摊位类型（1长期 2临时） */
    @Excel(name = "摊位类型", readConverterExp = "0=长期,1=临时")
    private String stallsType;

    /** 地址名称 */
    @Excel(name = "地址名称")
    private String address;

    /** 经度 */
    @Excel(name = "经度")
    private String lng;

    /** 纬度 */
    @Excel(name = "纬度")
    private String lat;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setStallsId(Long stallsId)
    {
        this.stallsId = stallsId;
    }

    public  Long getStallsId()
    {
        return stallsId;
    }
    public void setStallsType(String stallsType)
    {
        this.stallsType = stallsType;
    }

    public String getStallsType()
    {
        return stallsType;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setLng(String lng)
    {
        this.lng = lng;
    }

    public String getLng()
    {
        return lng;
    }
    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLat()
    {
        return lat;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("stallsId", getStallsId())
            .append("stallsType", getStallsType())
            .append("address", getAddress())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
