package cn.iocoder.yudao.module.system.dal.dataobject.order;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单 DO
 *
 * @author Begonia
 */
@TableName("system_order")
@KeySequence("system_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDO extends BaseDO {

    /**
     * 订单id
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
     * 类型，采购/销售
     *
     * 枚举 {@link TODO order_type 对应的类}
     */
    private String type;
    /**
     * 供应商/客户id
     */
    private Long clientId;
    /**
     * 供应商/客户名称
     */
    @TableField(exist = false)
    private String clientName;

    /**
     * 业务员id
     */
    private Long userId;
    /**
     * 业务员名称
     */
    @TableField(exist = false)
    private String userNickname;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 状态
     */
    private Integer status;

}