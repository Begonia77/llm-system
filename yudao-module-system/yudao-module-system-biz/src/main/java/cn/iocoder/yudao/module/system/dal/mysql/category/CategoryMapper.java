package cn.iocoder.yudao.module.system.dal.mysql.category;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.category.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.category.vo.*;

/**
 * 产品 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

   default PageResult<CategoryDO> selectPage(CategoryPageReqVO reqVO) {
       return selectPage(reqVO, new LambdaQueryWrapperX<CategoryDO>()
               .likeIfPresent(CategoryDO::getName, reqVO.getName())
               .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus())
               .betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
               .orderByDesc(CategoryDO::getId));
   }

    // IPage<CategoryDO> selectCategoryPage(IPage<CategoryDO> page, @Param("req") CategoryPageReqVO reqVO);

    // default PageResult<CategoryDO> selectPage(CategoryPageReqVO reqVO) {
    //     IPage<CategoryDO> page = MyBatisUtils.buildPage(reqVO);
    //     return new PageResult<>(selectCategoryPage(page, reqVO).getRecords(), page.getTotal());
    // }
}