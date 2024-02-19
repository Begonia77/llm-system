package cn.iocoder.yudao.module.system.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 订单新增/修改 Request VO")
@Data
public class OrderSaveReqVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15124")
    private Long id;

    @Schema(description = "订单单号")
    private String orderNumber;

    @Schema(description = "仓库id", example = "2348")
    private Long warehouseId;

    @Schema(description = "仓库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "仓库名称不能为空")
    private String warehouseName;

    @Schema(description = "类型，采购/销售", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "类型，采购/销售不能为空")
    private String type;

    @Schema(description = "供应商/客户id", example = "25524")
    private Long clientId;

    @Schema(description = "客户/供应商名称", example = "李四")
    private String clientName;

    @Schema(description = "业务员id", example = "4146")
    private Long userId;

    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    @Schema(description = "订单项数量")
    private Integer itemQuantity;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

}