package com.urban.function.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.urban.common.annotation.Log;
import com.urban.common.core.controller.BaseController;
import com.urban.common.core.domain.AjaxResult;
import com.urban.common.enums.BusinessType;
import com.urban.function.domain.SysComplain;
import com.urban.function.service.ISysComplainService;
import com.urban.common.utils.poi.ExcelUtil;
import com.urban.common.core.page.TableDataInfo;

/**
 * 投诉举报Controller
 * 
 * @author urban
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/function/complain")
public class SysComplainController extends BaseController
{
    @Autowired
    private ISysComplainService sysComplainService;

    /**
     * 查询投诉举报列表
     */
    @PreAuthorize("@ss.hasPermi('function:complain:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysComplain sysComplain)
    {
        startPage();
        List<SysComplain> list = sysComplainService.selectSysComplainList(sysComplain);
        return getDataTable(list);
    }

    /**
     * 导出投诉举报列表
     */
    @PreAuthorize("@ss.hasPermi('function:complain:export')")
    @Log(title = "投诉举报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysComplain sysComplain)
    {
        List<SysComplain> list = sysComplainService.selectSysComplainList(sysComplain);
        ExcelUtil<SysComplain> util = new ExcelUtil<SysComplain>(SysComplain.class);
        util.exportExcel(response, list, "投诉举报数据");
    }

    /**
     * 获取投诉举报详细信息
     */
    @PreAuthorize("@ss.hasPermi('function:complain:query')")
    @GetMapping(value = "/{complainId}")
    public AjaxResult getInfo(@PathVariable("complainId") Integer complainId)
    {
        return AjaxResult.success(sysComplainService.selectSysComplainByComplainId(complainId));
    }

    /**
     * 新增投诉举报
     */
    @PreAuthorize("@ss.hasPermi('function:complain:add')")
    @Log(title = "投诉举报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysComplain sysComplain)
    {
        return toAjax(sysComplainService.insertSysComplain(sysComplain));
    }

    /**
     * 修改投诉举报
     */
    @PreAuthorize("@ss.hasPermi('function:complain:edit')")
    @Log(title = "投诉举报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysComplain sysComplain)
    {
        return toAjax(sysComplainService.updateSysComplain(sysComplain));
    }

    /**
     * 删除投诉举报
     */
    @PreAuthorize("@ss.hasPermi('function:complain:remove')")
    @Log(title = "投诉举报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{complainIds}")
    public AjaxResult remove(@PathVariable Integer[] complainIds)
    {
        return toAjax(sysComplainService.deleteSysComplainByComplainIds(complainIds));
    }
}
