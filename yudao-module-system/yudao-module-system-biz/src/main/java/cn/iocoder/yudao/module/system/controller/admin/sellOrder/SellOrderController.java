package cn.iocoder.yudao.module.system.controller.admin.sellOrder;

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

import cn.iocoder.yudao.module.system.controller.admin.sellOrder.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sellOrder.SellOrderDO;
import cn.iocoder.yudao.module.system.service.sellOrder.SellOrderService;

@Tag(name = "管理后台 - 订单")
@RestController
@RequestMapping("/system/sell-order")
@Validated
public class SellOrderController {

    @Resource
    private SellOrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    @PreAuthorize("@ss.hasPermission('system:sellOrder:create')")
    public CommonResult<Long> createSellOrder(@Valid @RequestBody SellOrderSaveReqVO createReqVO) {
        return success(orderService.createSellOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单")
    @PreAuthorize("@ss.hasPermission('system:sellOrder:update')")
    public CommonResult<Boolean> updateSellOrder(@Valid @RequestBody SellOrderSaveReqVO updateReqVO) {
        orderService.updateSellOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:sellOrder:delete')")
    public CommonResult<Boolean> deleteSellOrder(@RequestParam("id") Long id) {
        orderService.deleteSellOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:sellOrder:query')")
    public CommonResult<SellOrderRespVO> getSellOrder(@RequestParam("id") Long id) {
        SellOrderDO sellOrder = orderService.getSellOrder(id);
        return success(BeanUtils.toBean(sellOrder, SellOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单分页")
    @PreAuthorize("@ss.hasPermission('system:sellOrder:query')")
    public CommonResult<PageResult<SellOrderRespVO>> getSellOrderPage(@Valid SellOrderPageReqVO pageReqVO) {
        PageResult<SellOrderDO> pageResult = orderService.getSellOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SellOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单 Excel")
    @PreAuthorize("@ss.hasPermission('system:sellOrder:export')")
    @OperateLog(type = EXPORT)
    public void exportSellOrderExcel(@Valid SellOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SellOrderDO> list = orderService.getSellOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订单.xls", "数据", SellOrderRespVO.class,
                        BeanUtils.toBean(list, SellOrderRespVO.class));
    }

}