package cn.iocoder.yudao.module.system.controller.admin.purchaseOrder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PurchaseOrderPageReqVO extends PageParam {

    @Schema(description = "订单单号")
    private Long orderNumber;

    @Schema(description = "仓库id", example = "张三")
    private Long warehouseId;
    
    @Schema(description = "客户/供应商id", example = "李四")
    private Long supplierId;

    @Schema(description = "业务员id", example = "李四")
    private Long staffId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}