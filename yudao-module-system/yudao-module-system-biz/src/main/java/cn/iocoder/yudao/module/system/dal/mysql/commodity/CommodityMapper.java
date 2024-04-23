package cn.iocoder.yudao.module.system.dal.mysql.commodity;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.commodity.CommodityDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.commodity.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;

/**
 * 产品 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface CommodityMapper extends BaseMapperX<CommodityDO> {

//    default PageResult<CommodityDO> selectPage(CommodityPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<CommodityDO>()
//                .likeIfPresent(CommodityDO::getName, reqVO.getName())
//                .likeIfPresent(CommodityDO::getCategory, reqVO.getCategory())
//                .likeIfPresent(CommodityDO::getBrand, reqVO.getBrand())
//                .eqIfPresent(CommodityDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(CommodityDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(CommodityDO::getId));
//    }

    IPage<CommodityDO> selectCommodityPage(IPage<CommodityDO> page, @Param("req") CommodityPageReqVO reqVO);

    default PageResult<CommodityDO> selectPage(CommodityPageReqVO reqVO) {
        IPage<CommodityDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectCommodityPage(page, reqVO).getRecords(), page.getTotal());
    }
}