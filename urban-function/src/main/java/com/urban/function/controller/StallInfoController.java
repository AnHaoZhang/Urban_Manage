package com.urban.function.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.urban.common.core.domain.entity.SysUser;
import com.urban.common.utils.ServletUtils;
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
import com.urban.function.domain.StallInfo;
import com.urban.function.service.IStallInfoService;
import com.urban.common.utils.poi.ExcelUtil;
import com.urban.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 摊位信息Controller
 *
 * @author urban
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/function/stall")
public class StallInfoController extends BaseController
{
    @Autowired
    private IStallInfoService stallInfoService;

    /**
     * 查询摊位信息列表
     */
    @PreAuthorize("@ss.hasPermi('function:stall:list')")
    @GetMapping("/list")
    public TableDataInfo list(StallInfo stallInfo)
    {
        startPage();
        List<StallInfo> list = stallInfoService.selectStallInfoList(stallInfo);
        return getDataTable(list);
    }

    /**
     * 导出摊位信息列表
     */
    @PreAuthorize("@ss.hasPermi('function:stall:export')")
    @Log(title = "摊位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StallInfo stallInfo)
    {
        List<StallInfo> list = stallInfoService.selectStallInfoList(stallInfo);
        ExcelUtil<StallInfo> util = new ExcelUtil<StallInfo>(StallInfo.class);
        util.exportExcel(response, list, "摊位信息数据");
    }

    @PreAuthorize("@ss.hasPermi('function:stall:address')")
    @GetMapping("/stallmap")
    public void stallmap(HttpServletResponse response, StallInfo stallInfo)
    {
        List<StallInfo> list = stallInfoService.getStalladdressInfoList(stallInfo);

    }


    /**
     * 导入摊位信息列表
     */
    @PreAuthorize("@ss.hasPermi('function:stall:import')")
    @Log(title = "摊位信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importStall")
    public AjaxResult importStall(MultipartFile file, boolean isUpdateSupport) throws Exception
    {
        ExcelUtil<StallInfo> util = new ExcelUtil<StallInfo>(StallInfo.class);
        List<StallInfo> stallInfoList = util.importExcel(file.getInputStream());
        StallInfo stallInfo = new StallInfo();
        Long stallsId= stallInfo.getStallsId();
        String message = stallInfoService.importStall(stallInfoList, isUpdateSupport, stallsId);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate(){
        ExcelUtil<StallInfo> util = new ExcelUtil<StallInfo>(StallInfo.class);
        return util.importTemplateExcel("摊位信息");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<StallInfo> util = new ExcelUtil<StallInfo>(StallInfo.class);
        util.importTemplateExcel(response, "摊位信息");
    }


    /**
     * 获取摊位信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('function:stall:query')")
    @GetMapping(value = "/{stallsId}")
    public AjaxResult getInfo(@PathVariable("stallsId") Long stallsId)
    {
        return AjaxResult.success(stallInfoService.selectStallInfoByStallsId(stallsId));
    }


    /**
     * 新增摊位信息
     */
    @PreAuthorize("@ss.hasPermi('function:stall:add')")
    @Log(title = "摊位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StallInfo stallInfo)
    {
        return toAjax(stallInfoService.insertStallInfo(stallInfo));
    }

    /**
     * 修改摊位信息
     */
    @PreAuthorize("@ss.hasPermi('function:stall:edit')")
    @Log(title = "摊位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StallInfo stallInfo)
    {
        return toAjax(stallInfoService.updateStallInfo(stallInfo));
    }

    /**
     * 删除摊位信息
     */
    @PreAuthorize("@ss.hasPermi('function:stall:remove')")
    @Log(title = "摊位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stallsIds}")
    public AjaxResult remove(@PathVariable Long[] stallsIds)
    {
        return toAjax(stallInfoService.deleteStallInfoByStallsIds(stallsIds));
    }

}
