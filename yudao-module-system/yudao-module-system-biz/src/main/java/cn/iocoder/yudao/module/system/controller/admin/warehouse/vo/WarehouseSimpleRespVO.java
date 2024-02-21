package cn.iocoder.yudao.module.system.controller.admin.warehouse.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 仓库精简 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WarehouseSimpleRespVO {

    @Schema(description = "仓库id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1933")
    @ExcelProperty("仓库id")
    private Long id;

    @Schema(description = "仓库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("仓库名称")
    private String name;

}