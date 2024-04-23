package cn.iocoder.yudao.module.system.controller.admin.category;

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

import cn.iocoder.yudao.module.system.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.module.system.service.category.CategoryService;

@Tag(name = "管理后台 - 分类")
@RestController
@RequestMapping("/system/category")
@Validated
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "创建分类")
    @PreAuthorize("@ss.hasPermission('system:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody CategorySaveReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分类")
    @PreAuthorize("@ss.hasPermission('system:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody CategorySaveReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:category:query')")
    public CommonResult<CategoryRespVO> getCategory(@RequestParam("id") Long id) {
        CategoryDO category = categoryService.getCategory(id);
        return success(BeanUtils.toBean(category, CategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分类分页")
    @PreAuthorize("@ss.hasPermission('system:category:query')")
    public CommonResult<PageResult<CategoryRespVO>> getCategoryPage(@Valid CategoryPageReqVO pageReqVO) {
        PageResult<CategoryDO> pageResult = categoryService.getCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分类 Excel")
    @PreAuthorize("@ss.hasPermission('system:category:export')")
    @OperateLog(type = EXPORT)
    public void exportCategoryExcel(@Valid CategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CategoryDO> list = categoryService.getCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "分类.xls", "数据", CategoryRespVO.class,
                        BeanUtils.toBean(list, CategoryRespVO.class));
    }

    @GetMapping({"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取分类精简信息列表", description = "只包含被开启的仓库，主要用于前端的下拉选项")
    public CommonResult<List<CategorySimpleRespVO>> getSimpleCategoryList() {
        List<CategoryDO> list = categoryService.getAllCategoryList();
        return success(BeanUtils.toBean(list, CategorySimpleRespVO.class));
    }

}