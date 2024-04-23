package cn.iocoder.yudao.module.system.controller.admin.commodity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 商品精简 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CommoditySimpleRespVO {

    @Schema(description = "商品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    @ExcelProperty("商品id")
    private Long id;

    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("商品名称")
    private String name;

    @Schema(description = "商品规格", requiredMode = Schema.RequiredMode.REQUIRED, example = "大")
    @ExcelProperty("商品规格")
    private String specification;

    @Schema(description = "进货价", requiredMode = Schema.RequiredMode.REQUIRED, example = "24288")
    @ExcelProperty("进货价")
    private BigDecimal purchasePrice;

    @Schema(description = "售出价", requiredMode = Schema.RequiredMode.REQUIRED, example = "10972")
    @ExcelProperty("售出价")
    private BigDecimal salePrice;

}