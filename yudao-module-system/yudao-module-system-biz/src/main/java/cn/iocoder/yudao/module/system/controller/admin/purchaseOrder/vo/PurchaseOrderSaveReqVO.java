package cn.iocoder.yudao.module.system.controller.admin.purchaseOrder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.module.system.controller.admin.purchaseOrderItem.vo.PurchaseOrderItemSaveReqVO;

@Schema(description = "管理后台 - 订单新增/修改 Request VO")
@Data
public class PurchaseOrderSaveReqVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15124")
    private Long id;

    @Schema(description = "仓库id", example = "2348")
    private Long warehouseId;

    @Schema(description = "供应商id", example = "25524")
    private Long supplierId;

    @Schema(description = "业务员id", example = "4146")
    private Long staffId;

    @Schema(description = "备注")
    private String remark;

    // 再加上订单项的对象数组
    @Schema(description = "订单项数组")
    private List<PurchaseOrderItemSaveReqVO> purchaseOrderItems;

}