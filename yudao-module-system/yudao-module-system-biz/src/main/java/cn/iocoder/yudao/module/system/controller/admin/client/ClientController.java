package cn.iocoder.yudao.module.system.controller.admin.client;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.client.vo.*;
import cn.iocoder.yudao.module.system.controller.admin.client.vo.ClientSimpleRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.client.ClientDO;
import cn.iocoder.yudao.module.system.service.client.ClientService;

@Tag(name = "管理后台 - 供应商/客户")
@RestController
@RequestMapping("/system/client")
@Validated
public class ClientController {

    @Resource
    private ClientService clientService;

    @PostMapping("/create")
    @Operation(summary = "创建供应商/客户")
    @PreAuthorize("@ss.hasPermission('system:client:create')")
    public CommonResult<Long> createClient(@Valid @RequestBody ClientSaveReqVO createReqVO) {
        return success(clientService.createClient(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新供应商/客户")
    @PreAuthorize("@ss.hasPermission('system:client:update')")
    public CommonResult<Boolean> updateClient(@Valid @RequestBody ClientSaveReqVO updateReqVO) {
        clientService.updateClient(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除供应商/客户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:client:delete')")
    public CommonResult<Boolean> deleteClient(@RequestParam("id") Long id) {
        clientService.deleteClient(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得供应商/客户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:client:query')")
    public CommonResult<ClientRespVO> getClient(@RequestParam("id") Long id) {
        ClientDO client = clientService.getClient(id);
        return success(BeanUtils.toBean(client, ClientRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得供应商/客户分页")
    @PreAuthorize("@ss.hasPermission('system:client:query')")
    public CommonResult<PageResult<ClientRespVO>> getClientPage(@Valid ClientPageReqVO pageReqVO) {
        PageResult<ClientDO> pageResult = clientService.getClientPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ClientRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出供应商/客户 Excel")
    @PreAuthorize("@ss.hasPermission('system:client:export')")
    @OperateLog(type = EXPORT)
    public void exportClientExcel(@Valid ClientPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ClientDO> list = clientService.getClientPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "供应商/客户.xls", "数据", ClientRespVO.class,
                        BeanUtils.toBean(list, ClientRespVO.class));
    }

    @GetMapping({"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取供应商/客户精简信息列表", description = "只包含被开启的用户，主要用于前端的下拉选项")
    public CommonResult<List<ClientSimpleRespVO>> getSimpleWarehouseList() {
        List<ClientDO> list = clientService.getAllClientList();
        return success(BeanUtils.toBean(list, ClientSimpleRespVO.class));
    }

}