package cn.iocoder.yudao.module.system.controller.admin.products.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 产品新增/修改 Request VO")
@Data
public class ProductsSaveReqVO {

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31776")
    private Long id;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "产品名称不能为空")
    private String name;

    @Schema(description = "产品分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "产品分类不能为空")
    private String category;

    @Schema(description = "品牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "品牌不能为空")
    private String brand;

    @Schema(description = "产品描述", example = "你猜")
    private String description;

    @Schema(description = "产品图片", example = "https://www.iocoder.cn")
    private String imageUrl;

    @Schema(description = "进货价", requiredMode = Schema.RequiredMode.REQUIRED, example = "24288")
    @NotNull(message = "进货价不能为空")
    private BigDecimal purchasePrice;

    @Schema(description = "售出价", requiredMode = Schema.RequiredMode.REQUIRED, example = "10972")
    @NotNull(message = "售出价不能为空")
    private BigDecimal salePrice;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}