package cn.iocoder.yudao.module.system.dal.dataobject.client;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 供应商/客户 DO
 *
 * @author Begonia
 */
@TableName("client")
@KeySequence("client_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDO extends BaseDO {

    /**
     * 供应商/客户id
     */
    @TableId
    private Long id;
    /**
     * 联系人
     */
    private String name;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 公司名称
     */
    // private String companyName;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private String status;

}