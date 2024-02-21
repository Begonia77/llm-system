package cn.iocoder.yudao.module.system.controller.admin.client.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 供应商/客户精简 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClientSimpleRespVO {

    @Schema(description = "供应商/客户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1933")
    @ExcelProperty("供应商/客户id")
    private Long id;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("联系人")
    private String name;

}