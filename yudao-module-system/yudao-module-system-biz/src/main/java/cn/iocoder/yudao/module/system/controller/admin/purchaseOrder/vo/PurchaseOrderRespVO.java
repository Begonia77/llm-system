package cn.iocoder.yudao.module.system.controller.admin.purchaseOrder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PurchaseOrderRespVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15124")
    @ExcelProperty("订单id")
    private Long id;

    @Schema(description = "订单单号")
    @ExcelProperty("订单单号")
    private Long orderNumber;

    @Schema(description = "仓库id", example = "2348")
    @ExcelProperty("仓库id")
    private Long warehouseId;

    @Schema(description = "仓库名称")
    @ExcelProperty("仓库名称")
    private String warehouseName;

    @Schema(description = "供应商id", example = "25524")
    @ExcelProperty("供应商id")
    private Long supplierId;

    @Schema(description = "供应商名称")
    @ExcelProperty("供应商名称")
    private String supplierName;

    @Schema(description = "业务员id", example = "4146")
    @ExcelProperty("业务员id")
    private Long staffId;

    @Schema(description = "业务员名称")
    @ExcelProperty("业务员名称")
    private String userNickname;

    @Schema(description = "购买总数")
    @ExcelProperty("购买总数")
    private Integer totalQuantity;

    @Schema(description = "总金额")
    @ExcelProperty("总金额")
    private BigDecimal totalAmount;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}