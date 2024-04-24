package cn.iocoder.yudao.module.system.controller.admin.sellOrderItem;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
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

import cn.iocoder.yudao.module.system.controller.admin.sellOrderItem.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sellOrderItem.SellOrderItemDO;
import cn.iocoder.yudao.module.system.service.sellOrderItem.SellOrderItemService;

@Tag(name = "管理后台 - 订单项")
@RestController
@RequestMapping("/system/sell-order-item")
@Validated
public class SellOrderItemController {

    @Resource
    private SellOrderItemService sellOrderItemService;

    @PostMapping("/create")
    @Operation(summary = "创建订单项")
    @PreAuthorize("@ss.hasPermission('system:order-item:create')")
    public CommonResult<Long> createSellOrderItem(@Valid @RequestBody SellOrderItemSaveReqVO createReqVO) {
        return success(sellOrderItemService.createSellOrderItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单项")
    @PreAuthorize("@ss.hasPermission('system:order-item:update')")
    public CommonResult<Boolean> updateSellOrderItem(@Valid @RequestBody SellOrderItemSaveReqVO updateReqVO) {
        sellOrderItemService.updateSellOrderItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:order-item:delete')")
    public CommonResult<Boolean> deleteSellOrderItem(@RequestParam("id") Long id) {
        sellOrderItemService.deleteSellOrderItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:order-item:query')")
    public CommonResult<SellOrderItemRespVO> getSellOrderItem(@RequestParam("id") Long id) {
        SellOrderItemDO sellOrderItem = sellOrderItemService.getSellOrderItem(id);
        return success(BeanUtils.toBean(sellOrderItem, SellOrderItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单项分页")
    @PreAuthorize("@ss.hasPermission('system:order-item:query')")
    public CommonResult<PageResult<SellOrderItemRespVO>> getSellOrderItemPage(@Valid SellOrderItemPageReqVO pageReqVO) {
        PageResult<SellOrderItemDO> pageResult = sellOrderItemService.getSellOrderItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SellOrderItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单项 Excel")
    @PreAuthorize("@ss.hasPermission('system:order-item:export')")
    @OperateLog(type = EXPORT)
    public void exportSellOrderItemExcel(@Valid SellOrderItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SellOrderItemDO> list = sellOrderItemService.getSellOrderItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订单项.xls", "数据", SellOrderItemRespVO.class,
                        BeanUtils.toBean(list, SellOrderItemRespVO.class));
    }

}