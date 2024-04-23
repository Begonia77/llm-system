package cn.iocoder.yudao.module.system.dal.dataobject.brand;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 品牌 DO
 *
 * @author Begonia
 */
@TableName("brand")
@KeySequence("brand_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDO extends BaseDO {

    /**
     * 品牌id
     */
    @TableId
    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;
}