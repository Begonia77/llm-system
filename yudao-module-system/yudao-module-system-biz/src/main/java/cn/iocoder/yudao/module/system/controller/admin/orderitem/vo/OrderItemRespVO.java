package cn.iocoder.yudao.module.system.controller.admin.orderitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 订单项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderItemRespVO {

    @Schema(description = "订单项id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29586")
    @ExcelProperty("订单项id")
    private Long id;

    @Schema(description = "产品id", example = "8524")
    @ExcelProperty("产品id")
    private Long productId;

    @Schema(description = "订单id", example = "27053")
    @ExcelProperty("订单id")
    private Long orderId;

    @Schema(description = "数量")
    @ExcelProperty("数量")
    private Integer quantity;

    @Schema(description = "价格", example = "14343")
    @ExcelProperty("价格")
    private BigDecimal price;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remarks;

    @Schema(description = "状态", example = "1")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}