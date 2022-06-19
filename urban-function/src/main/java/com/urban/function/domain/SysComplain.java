package com.urban.function.domain;

import com.urban.common.annotation.Excel;
import com.urban.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 投诉举报对象 sys_complain
 *
 * @author urban
 * @date 2022-05-12
 */
public class SysComplain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投诉ID */
    private Integer complainId;

    /** 投诉标题 */
    @Excel(name = "投诉标题")
    private String complainTitle;

    /** 投诉类型（1安全 2服务） */
    @Excel(name = "投诉类型", readConverterExp = "1=安全,2=服务")
    private String complainType;

    /** 投诉内容 */
    @Excel(name = "投诉内容")
    private String complainContent;

    /** 投诉状态（0正常 1关闭） */
    @Excel(name = "投诉状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    public void setComplainId(Integer complainId)
    {
        this.complainId = complainId;
    }

    public Integer getComplainId()
    {
        return complainId;
    }
    public void setComplainTitle(String complainTitle)
    {
        this.complainTitle = complainTitle;
    }

    public String getComplainTitle()
    {
        return complainTitle;
    }
    public void setComplainType(String complainType)
    {
        this.complainType = complainType;
    }

    public String getComplainType()
    {
        return complainType;
    }
    public void setComplainContent(String complainContent)
    {
        this.complainContent = complainContent;
    }

    public String getComplainContent()
    {
        return complainContent;
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
            .append("complainId", getComplainId())
            .append("complainTitle", getComplainTitle())
            .append("complainType", getComplainType())
            .append("complainContent", getComplainContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
