package cn.iocoder.yudao.module.system.controller.admin.brand.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 品牌精简 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BrandSimpleRespVO {

    @Schema(description = "品牌id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    @ExcelProperty("品牌id")
    private Long id;

    @Schema(description = "品牌名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "apple")
    @ExcelProperty("品牌名称")
    private String name;
}