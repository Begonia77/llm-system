package cn.iocoder.yudao.module.system.controller.admin.brand;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.system.controller.admin.brand.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.brand.BrandDO;
import cn.iocoder.yudao.module.system.service.brand.BrandService;

@Tag(name = "管理后台 - 品牌")
@RestController
@RequestMapping("/system/brand")
@Validated
public class BrandController {

    @Resource
    private BrandService brandService;

    @PostMapping("/create")
    @Operation(summary = "创建品牌")
    @PreAuthorize("@ss.hasPermission('system:brand:create')")
    public CommonResult<Long> createBrand(@Valid @RequestBody BrandSaveReqVO createReqVO) {
        return success(brandService.createBrand(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新品牌")
    @PreAuthorize("@ss.hasPermission('system:brand:update')")
    public CommonResult<Boolean> updateBrand(@Valid @RequestBody BrandSaveReqVO updateReqVO) {
        brandService.updateBrand(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除品牌")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:brand:delete')")
    public CommonResult<Boolean> deleteBrand(@RequestParam("id") Long id) {
        brandService.deleteBrand(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得品牌")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:brand:query')")
    public CommonResult<BrandRespVO> getBrand(@RequestParam("id") Long id) {
        BrandDO brand = brandService.getBrand(id);
        return success(BeanUtils.toBean(brand, BrandRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得品牌分页")
    @PreAuthorize("@ss.hasPermission('system:brand:query')")
    public CommonResult<PageResult<BrandRespVO>> getBrandPage(@Valid BrandPageReqVO pageReqVO) {
        PageResult<BrandDO> pageResult = brandService.getBrandPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BrandRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出品牌 Excel")
    @PreAuthorize("@ss.hasPermission('system:brand:export')")
    @OperateLog(type = EXPORT)
    public void exportBrandExcel(@Valid BrandPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BrandDO> list = brandService.getBrandPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "品牌.xls", "数据", BrandRespVO.class,
                        BeanUtils.toBean(list, BrandRespVO.class));
    }

    @GetMapping({"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取品牌精简信息列表", description = "只包含被开启的仓库，主要用于前端的下拉选项")
    public CommonResult<List<BrandSimpleRespVO>> getSimpleBrandList() {
        List<BrandDO> list = brandService.getAllBrandList();
        return success(BeanUtils.toBean(list, BrandSimpleRespVO.class));
    }

}