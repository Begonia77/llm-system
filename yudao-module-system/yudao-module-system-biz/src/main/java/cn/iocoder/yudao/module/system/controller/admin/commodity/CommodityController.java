package cn.iocoder.yudao.module.system.controller.admin.commodity;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.commodity.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.commodity.CommodityDO;
import cn.iocoder.yudao.module.system.service.commodity.CommodityService;

@Tag(name = "管理后台 - 商品")
@RestController
@RequestMapping("/system/commodity")
@Validated
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @PostMapping("/create")
    @Operation(summary = "创建商品")
    @PreAuthorize("@ss.hasPermission('system:commodity:create')")
    public CommonResult<Long> createCommodity(@Valid @RequestBody CommoditySaveReqVO createReqVO) {
        return success(commodityService.createCommodity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品")
    @PreAuthorize("@ss.hasPermission('system:commodity:update')")
    public CommonResult<Boolean> updateCommodity(@Valid @RequestBody CommoditySaveReqVO updateReqVO) {
        commodityService.updateCommodity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商品")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:commodity:delete')")
    public CommonResult<Boolean> deleteCommodity(@RequestParam("id") Long id) {
        commodityService.deleteCommodity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:commodity:query')")
    public CommonResult<CommodityRespVO> getCommodity(@RequestParam("id") Long id) {
        CommodityDO commodity = commodityService.getCommodity(id);
        return success(BeanUtils.toBean(commodity, CommodityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商品分页")
    @PreAuthorize("@ss.hasPermission('system:commodity:query')")
    public CommonResult<PageResult<CommodityRespVO>> getCommodityPage(@Valid CommodityPageReqVO pageReqVO) {
        PageResult<CommodityDO> pageResult = commodityService.getCommodityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CommodityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商品 Excel")
    @PreAuthorize("@ss.hasPermission('system:commodity:export')")
    @OperateLog(type = EXPORT)
    public void exportCommodityExcel(@Valid CommodityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CommodityDO> list = commodityService.getCommodityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商品.xls", "数据", CommodityRespVO.class,
                        BeanUtils.toBean(list, CommodityRespVO.class));
    }

    @GetMapping({"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取商品精简信息列表", description = "只包含被开启的商品，主要用于前端的下拉选项")
    public CommonResult<List<CommoditySimpleRespVO>> getSimpleCommodityList() {
        List<CommodityDO> list = commodityService.getAllCommodityList();
        return success(BeanUtils.toBean(list, CommoditySimpleRespVO.class));
    }

}