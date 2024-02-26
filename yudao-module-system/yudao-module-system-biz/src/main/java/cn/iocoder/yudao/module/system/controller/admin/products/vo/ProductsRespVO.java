package cn.iocoder.yudao.module.system.controller.admin.products.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 产品 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProductsRespVO {

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    @ExcelProperty("产品id")
    private Long id;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("产品名称")
    private String name;

    @Schema(description = "产品分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("产品分类")
    private String category;

    @Schema(description = "品牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("品牌")
    private String brand;

    @Schema(description = "产品描述", example = "你猜")
    @ExcelProperty("产品描述")
    private String description;

    @Schema(description = "产品图片", example = "https://www.iocoder.cn")
    @ExcelProperty("产品图片")
    private String imageUrl;

    @Schema(description = "进货价", requiredMode = Schema.RequiredMode.REQUIRED, example = "24288")
    @ExcelProperty("进货价")
    private BigDecimal purchasePrice;

    @Schema(description = "售出价", requiredMode = Schema.RequiredMode.REQUIRED, example = "10972")
    @ExcelProperty("售出价")
    private BigDecimal salePrice;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remarks;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "库存", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @ExcelProperty("库存")
    private Integer inventory;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}