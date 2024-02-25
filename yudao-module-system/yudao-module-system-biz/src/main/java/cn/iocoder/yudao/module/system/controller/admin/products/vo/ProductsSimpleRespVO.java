package cn.iocoder.yudao.module.system.controller.admin.products.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 产品精简 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProductsSimpleRespVO {

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    @ExcelProperty("产品id")
    private Long id;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("产品名称")
    private String name;

    @Schema(description = "进货价", requiredMode = Schema.RequiredMode.REQUIRED, example = "24288")
    @ExcelProperty("进货价")
    private BigDecimal purchasePrice;

    @Schema(description = "售出价", requiredMode = Schema.RequiredMode.REQUIRED, example = "10972")
    @ExcelProperty("售出价")
    private BigDecimal salePrice;

}