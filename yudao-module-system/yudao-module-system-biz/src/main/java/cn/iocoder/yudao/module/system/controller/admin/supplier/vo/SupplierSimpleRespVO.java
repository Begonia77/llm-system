package cn.iocoder.yudao.module.system.controller.admin.supplier.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 供应商精简 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SupplierSimpleRespVO {

    @Schema(description = "供应商id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1933")
    @ExcelProperty("供应商id")
    private Long id;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("联系人")
    private String name;

}