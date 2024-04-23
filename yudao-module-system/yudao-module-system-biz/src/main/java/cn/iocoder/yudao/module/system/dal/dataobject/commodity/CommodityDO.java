package cn.iocoder.yudao.module.system.dal.dataobject.commodity;

import lombok.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品 DO
 *
 * @author Begonia
 */
@TableName("system_commodity")
@KeySequence("system_commodity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommodityDO extends BaseDO {

    /**
     * 商品id
     */
    @TableId
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 规格
     */
    private String specification;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品图片
     */
    private String imageUrl;
    /**
     * 进货价
     */
    private BigDecimal purchasePrice;
    /**
     * 售出价
     */
    private BigDecimal salePrice;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;
    /**
     * 库存
     */
    @TableField(exist = false)
    private Integer inventory;

}