package cn.iocoder.yudao.module.system.dal.dataobject.warehouse;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 仓库 DO
 *
 * @author Begonia
 */
@TableName("system_warehouse")
@KeySequence("system_warehouse_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDO extends BaseDO {

    /**
     * 仓库id
     */
    @TableId
    private Long id;
    /**
     * 仓库名称
     */
    private String name;
    /**
     * 仓库类型
     *
     * 枚举 {@link TODO warehouse_type 对应的类}
     */
    private String type;
    /**
     * 仓库地址
     */
    private String address;
    /**
     * 仓库负责人id
     */
    private Long userId;
    /**
     * 仓库负责人
     */
    private String userName;
    /**
     * 库存上限
     */
    private Integer maxInventory;
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

}