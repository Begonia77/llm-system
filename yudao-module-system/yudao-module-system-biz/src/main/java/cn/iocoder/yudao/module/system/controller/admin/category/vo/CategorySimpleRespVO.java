package cn.iocoder.yudao.module.system.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 分类精简 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CategorySimpleRespVO {

    @Schema(description = "分类id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    @ExcelProperty("分类id")
    private Long id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("分类名称")
    private String name;

}