package cn.iocoder.yudao.module.system.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.module.system.controller.admin.orderitem.vo.OrderItemSaveReqVO;

@Schema(description = "管理后台 - 订单新增/修改 Request VO")
@Data
public class OrderSaveReqVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15124")
    private Long id;

    @Schema(description = "订单单号")
    private Long orderNumber;

    @Schema(description = "仓库id", example = "2348")
    private Long warehouseId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "类型不能为空")
    private String type;

    @Schema(description = "供应商/客户id", example = "25524")
    private Long clientId;

    @Schema(description = "业务员id", example = "4146")
    private Long userId;

    @Schema(description = "备注")
    private String remarks;

    // 再加上订单项的对象数组
    @Schema(description = "订单项数组")
    private List<OrderItemSaveReqVO> orderItems;

}