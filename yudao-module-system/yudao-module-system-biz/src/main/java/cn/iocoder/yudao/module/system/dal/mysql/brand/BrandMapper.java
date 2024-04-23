package cn.iocoder.yudao.module.system.dal.mysql.brand;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.brand.BrandDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.brand.vo.*;

/**
 * 产品 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface BrandMapper extends BaseMapperX<BrandDO> {

   default PageResult<BrandDO> selectPage(BrandPageReqVO reqVO) {
       return selectPage(reqVO, new LambdaQueryWrapperX<BrandDO>()
               .likeIfPresent(BrandDO::getName, reqVO.getName())
               .eqIfPresent(BrandDO::getStatus, reqVO.getStatus())
               .betweenIfPresent(BrandDO::getCreateTime, reqVO.getCreateTime())
               .orderByDesc(BrandDO::getId));
   }

    // IPage<BrandDO> selectBrandPage(IPage<BrandDO> page, @Param("req") BrandPageReqVO reqVO);

    // default PageResult<BrandDO> selectPage(BrandPageReqVO reqVO) {
    //     IPage<BrandDO> page = MyBatisUtils.buildPage(reqVO);
    //     return new PageResult<>(selectBrandPage(page, reqVO).getRecords(), page.getTotal());
    // }
}