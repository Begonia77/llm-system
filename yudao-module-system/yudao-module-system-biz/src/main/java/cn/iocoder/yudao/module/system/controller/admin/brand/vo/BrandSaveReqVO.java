package cn.iocoder.yudao.module.system.controller.admin.brand.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 品牌新增/修改 Request VO")
@Data
public class BrandSaveReqVO {

    @Schema(description = "品牌id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    private Long id;

    @Schema(description = "品牌名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "品牌名称不能为空")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}