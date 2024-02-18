package cn.iocoder.yudao.module.system.controller.admin.client.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 供应商/客户新增/修改 Request VO")
@Data
public class ClientSaveReqVO {

    @Schema(description = "供应商/客户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1933")
    private Long id;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "联系人不能为空")
    private String name;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13185746375")
    @NotEmpty(message = "联系电话不能为空")
    private String mobile;

    @Schema(description = "公司名称", example = "王五有限公司")
    private String companyName;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "状态", example = "1")
    private String status;

}