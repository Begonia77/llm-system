package cn.iocoder.yudao.module.system.controller.admin.orderitem;

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

import cn.iocoder.yudao.module.system.controller.admin.orderitem.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.orderitem.OrderItemDO;
import cn.iocoder.yudao.module.system.service.orderitem.OrderItemService;

@Tag(name = "管理后台 - 订单项")
@RestController
@RequestMapping("/system/order-item")
@Validated
public class OrderItemController {

    @Resource
    private OrderItemService orderItemService;

    @PostMapping("/create")
    @Operation(summary = "创建订单项")
    @PreAuthorize("@ss.hasPermission('system:order-item:create')")
    public CommonResult<Long> createOrderItem(@Valid @RequestBody OrderItemSaveReqVO createReqVO) {
        return success(orderItemService.createOrderItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单项")
    @PreAuthorize("@ss.hasPermission('system:order-item:update')")
    public CommonResult<Boolean> updateOrderItem(@Valid @RequestBody OrderItemSaveReqVO updateReqVO) {
        orderItemService.updateOrderItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:order-item:delete')")
    public CommonResult<Boolean> deleteOrderItem(@RequestParam("id") Long id) {
        orderItemService.deleteOrderItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:order-item:query')")
    public CommonResult<OrderItemRespVO> getOrderItem(@RequestParam("id") Long id) {
        OrderItemDO orderItem = orderItemService.getOrderItem(id);
        return success(BeanUtils.toBean(orderItem, OrderItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单项分页")
    @PreAuthorize("@ss.hasPermission('system:order-item:query')")
    public CommonResult<PageResult<OrderItemRespVO>> getOrderItemPage(@Valid OrderItemPageReqVO pageReqVO) {
        PageResult<OrderItemDO> pageResult = orderItemService.getOrderItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单项 Excel")
    @PreAuthorize("@ss.hasPermission('system:order-item:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderItemExcel(@Valid OrderItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderItemDO> list = orderItemService.getOrderItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订单项.xls", "数据", OrderItemRespVO.class,
                        BeanUtils.toBean(list, OrderItemRespVO.class));
    }

}