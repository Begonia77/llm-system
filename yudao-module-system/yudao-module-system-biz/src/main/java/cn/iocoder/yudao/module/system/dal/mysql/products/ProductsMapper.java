package cn.iocoder.yudao.module.system.dal.mysql.products;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.products.ProductsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.products.vo.*;

/**
 * 产品 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface ProductsMapper extends BaseMapperX<ProductsDO> {

    default PageResult<ProductsDO> selectPage(ProductsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductsDO>()
                .likeIfPresent(ProductsDO::getName, reqVO.getName())
                .eqIfPresent(ProductsDO::getCategory, reqVO.getCategory())
                .eqIfPresent(ProductsDO::getBrand, reqVO.getBrand())
                .eqIfPresent(ProductsDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ProductsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductsDO::getId));
    }

}