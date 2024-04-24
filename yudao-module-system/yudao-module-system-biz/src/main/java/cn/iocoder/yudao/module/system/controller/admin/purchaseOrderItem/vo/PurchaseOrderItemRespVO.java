package cn.iocoder.yudao.module.system.controller.admin.purchaseOrderItem.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 订单项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PurchaseOrderItemRespVO {

    @Schema(description = "订单项id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29586")
    @ExcelProperty("订单项id")
    private Long id;

    @Schema(description = "产品id", example = "8524")
    @ExcelProperty("产品id")
    private Long commodityId;

    @Schema(description = "产品名")
    @ExcelProperty("产品名")
    private String commodityName;

    @Schema(description = "订单id", example = "27053")
    @ExcelProperty("订单id")
    private Long purchaseOrderId;

    @Schema(description = "数量")
    @ExcelProperty("数量")
    private Integer quantity;

    @Schema(description = "价格", example = "14343")
    @ExcelProperty("价格")
    private BigDecimal price;

    @Schema(description = "总金额", example = "14343")
    @ExcelProperty("总金额")
    private BigDecimal totalAmount;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "状态", example = "1")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}