package cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrderItem;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单项 DO
 *
 * @author Begonia
 */
@TableName("purchase_order_item")
@KeySequence("purchase_order_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderItemDO extends BaseDO {

    /**
     * 采购订单项id
     */
    @TableId
    private Long id;
    /**
     * 产品id
     */
    private Long commodityId;
    /**
     * 产品名
     */
    @TableField(exist = false)
    private String commodityName;
    /**
     * 采购订单id
     */
    private Long purchaseOrderId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 总金额
     */
    @TableField(exist = false)
    private BigDecimal totalAmount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private String status;

}