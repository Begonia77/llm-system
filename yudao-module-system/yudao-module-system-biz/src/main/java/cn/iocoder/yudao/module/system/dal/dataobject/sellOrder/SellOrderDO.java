package cn.iocoder.yudao.module.system.dal.dataobject.sellOrder;

import lombok.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单 DO
 *
 * @author Begonia
 */
@TableName("sell_order")
@KeySequence("sell_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellOrderDO extends BaseDO {

    /**
     * 销售订单id
     */
    @TableId
    private Long id;
    /**
     * 订单单号
     */
    private Long orderNumber;
    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    @TableField(exist = false)
    private String warehouseName;

    /**
     * 客户id
     */
    private Long clientId;
    /**
     * 客户名称
     */
    @TableField(exist = false)
    private String clientName;

    /**
     * 业务员id
     */
    private Long staffId;
    /**
     * 业务员名称
     */
    @TableField(exist = false)
    private String userNickname;

    /**
     * 购买总数
     */
    @TableField(exist = false)
    private Integer totalQuantity;
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
    private Integer status;

}