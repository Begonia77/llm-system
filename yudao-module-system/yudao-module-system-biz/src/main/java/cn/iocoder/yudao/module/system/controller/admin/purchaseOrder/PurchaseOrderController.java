package cn.iocoder.yudao.module.system.controller.admin.purchaseOrder;

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

import cn.iocoder.yudao.module.system.controller.admin.purchaseOrder.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrder.PurchaseOrderDO;
import cn.iocoder.yudao.module.system.service.purchaseOrder.PurchaseOrderService;

@Tag(name = "管理后台 - 订单")
@RestController
@RequestMapping("/system/purchase-order")
@Validated
public class PurchaseOrderController {

    @Resource
    private PurchaseOrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    @PreAuthorize("@ss.hasPermission('system:purchaseOrder:create')")
    public CommonResult<Long> createPurchaseOrder(@Valid @RequestBody PurchaseOrderSaveReqVO createReqVO) {
        return success(orderService.createPurchaseOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单")
    @PreAuthorize("@ss.hasPermission('system:purchaseOrder:update')")
    public CommonResult<Boolean> updatePurchaseOrder(@Valid @RequestBody PurchaseOrderSaveReqVO updateReqVO) {
        orderService.updatePurchaseOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:purchaseOrder:delete')")
    public CommonResult<Boolean> deletePurchaseOrder(@RequestParam("id") Long id) {
        orderService.deletePurchaseOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:purchaseOrder:query')")
    public CommonResult<PurchaseOrderRespVO> getPurchaseOrder(@RequestParam("id") Long id) {
        PurchaseOrderDO purchaseOrder = orderService.getPurchaseOrder(id);
        return success(BeanUtils.toBean(purchaseOrder, PurchaseOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单分页")
    @PreAuthorize("@ss.hasPermission('system:purchaseOrder:query')")
    public CommonResult<PageResult<PurchaseOrderRespVO>> getPurchaseOrderPage(@Valid PurchaseOrderPageReqVO pageReqVO) {
        PageResult<PurchaseOrderDO> pageResult = orderService.getPurchaseOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PurchaseOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单 Excel")
    @PreAuthorize("@ss.hasPermission('system:purchaseOrder:export')")
    @OperateLog(type = EXPORT)
    public void exportPurchaseOrderExcel(@Valid PurchaseOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PurchaseOrderDO> list = orderService.getPurchaseOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订单.xls", "数据", PurchaseOrderRespVO.class,
                        BeanUtils.toBean(list, PurchaseOrderRespVO.class));
    }

}