package cn.iocoder.yudao.module.system.controller.admin.orderitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 订单项新增/修改 Request VO")
@Data
public class OrderItemSaveReqVO {

    @Schema(description = "订单项id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29586")
    private Long id;

    @Schema(description = "产品名称", example = "张三")
    private String productName;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "价格", example = "14343")
    private BigDecimal price;

    @Schema(description = "单项总金额")
    private BigDecimal totalAmount;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "状态", example = "1")
    private String status;

}