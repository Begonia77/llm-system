package cn.iocoder.yudao.module.system.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 分类新增/修改 Request VO")
@Data
public class CategorySaveReqVO {

    @Schema(description = "分类id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    private Long id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "分类名称不能为空")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}