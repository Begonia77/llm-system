package cn.iocoder.yudao.module.system.dal.dataobject.orderitem;

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
@TableName("system_order_item")
@KeySequence("system_order_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDO extends BaseDO {

    /**
     * 订单项id
     */
    @TableId
    private Long id;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 单项总金额
     */
    private BigDecimal totalAmount;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 状态
     */
    private String status;

}