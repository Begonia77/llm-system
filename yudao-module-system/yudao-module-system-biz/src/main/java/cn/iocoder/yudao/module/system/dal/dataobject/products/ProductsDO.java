package cn.iocoder.yudao.module.system.dal.dataobject.products;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品 DO
 *
 * @author Begonia
 */
@TableName("system_products")
@KeySequence("system_products_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDO extends BaseDO {

    /**
     * 产品id
     */
    @TableId
    private Long id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品分类
     */
    private String category;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 产品描述
     */
    private String description;
    /**
     * 产品图片
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