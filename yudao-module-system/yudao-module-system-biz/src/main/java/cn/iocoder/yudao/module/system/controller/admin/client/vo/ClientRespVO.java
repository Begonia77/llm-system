package cn.iocoder.yudao.module.system.controller.admin.client.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 供应商/客户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClientRespVO {

    @Schema(description = "供应商/客户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1933")
    @ExcelProperty("供应商/客户id")
    private Long id;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("联系人")
    private String name;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13185746375")
    @ExcelProperty("联系电话")
    private String mobile;

    // @Schema(description = "公司名称", example = "王五有限公司")
    // @ExcelProperty("公司名称")
    // private String companyName;

    @Schema(description = "电子邮箱")
    @ExcelProperty("电子邮箱")
    private String email;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "状态", example = "1")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}