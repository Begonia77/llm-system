package cn.iocoder.yudao.module.system.controller.admin.warehouse.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 仓库分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WarehousePageReqVO extends PageParam {

    @Schema(description = "仓库名称", example = "赵六")
    private String name;

    @Schema(description = "仓库类型", example = "1")
    private String type;

    @Schema(description = "仓库负责人", example = "张三")
    private String userName;

    @Schema(description = "库存上限", example = "1000")
    private Integer maxInventory;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}