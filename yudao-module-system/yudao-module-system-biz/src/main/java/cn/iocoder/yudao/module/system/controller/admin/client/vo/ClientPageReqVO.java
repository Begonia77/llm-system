package cn.iocoder.yudao.module.system.controller.admin.client.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 供应商/客户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientPageReqVO extends PageParam {

    @Schema(description = "联系人", example = "王五")
    private String name;

    @Schema(description = "联系电话", example = "13185746375")
    private String mobile;

    @Schema(description = "公司名称", example = "王五有限公司")
    private String companyName;

    @Schema(description = "状态", example = "1")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}