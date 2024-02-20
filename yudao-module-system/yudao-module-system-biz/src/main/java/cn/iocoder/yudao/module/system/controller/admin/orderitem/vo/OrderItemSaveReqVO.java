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

    @Schema(description = "产品id", example = "12")
    private Long productId;

    @Schema(description = "订单id", example = "12")
    private Long orderId;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "价格", example = "14343")
    private BigDecimal price;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "状态", example = "1")
    private String status;

}