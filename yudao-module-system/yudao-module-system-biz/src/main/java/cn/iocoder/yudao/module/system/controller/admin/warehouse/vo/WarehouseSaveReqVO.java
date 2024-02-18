package cn.iocoder.yudao.module.system.controller.admin.warehouse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 仓库新增/修改 Request VO")
@Data
public class WarehouseSaveReqVO {

    @Schema(description = "仓库id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1933")
    private Long id;

    @Schema(description = "仓库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "仓库名称不能为空")
    private String name;

    @Schema(description = "仓库类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "仓库类型不能为空")
    private String type;

    @Schema(description = "仓库地址")
    private String address;

    @Schema(description = "仓库负责人", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "仓库负责人不能为空")
    private String userName;

    @Schema(description = "库存上限", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @NotNull(message = "库存上限不能为空")
    private Integer maxInventory;

    @Schema(description = "备注", example = "0")
    private String remarks;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}