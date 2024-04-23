package cn.iocoder.yudao.module.system.controller.admin.warehouse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 仓库 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WarehouseRespVO {

    @Schema(description = "仓库id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1933")
    @ExcelProperty("仓库id")
    private Long id;

    @Schema(description = "仓库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("仓库名称")
    private String name;

    @Schema(description = "仓库类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "仓库类型", converter = DictConvert.class)
    @DictFormat("warehouse_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String type;

    @Schema(description = "仓库地址")
    @ExcelProperty("仓库地址")
    private String address;

    @Schema(description = "仓库负责人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "22")
    @ExcelProperty("仓库负责人id")
    private Long staffId;

    @Schema(description = "仓库负责人名称")
    @ExcelProperty("仓库负责人名称")
    private String userNickname;

    @Schema(description = "库存上限", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @ExcelProperty("库存上限")
    private Integer maxInventory;

    @Schema(description = "备注", example = "0")
    @ExcelProperty("备注")
    private String remarks;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "库存", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @ExcelProperty("库存")
    private Integer inventory;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}