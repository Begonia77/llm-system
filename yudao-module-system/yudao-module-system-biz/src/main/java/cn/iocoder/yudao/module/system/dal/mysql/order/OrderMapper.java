package cn.iocoder.yudao.module.system.dal.mysql.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.system.dal.dataobject.order.OrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.order.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 订单 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface OrderMapper extends BaseMapperX<OrderDO> {
    IPage<OrderDO> selectOrderPage(IPage<OrderDO> page, @Param("req") OrderPageReqVO reqVO);
//    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
//                // .eqIfPresent(OrderDO::getOrderNumber, reqVO.getOrderNumber())
//                .eqIfPresent(OrderDO::getType, reqVO.getType())
//                .eqIfPresent(OrderDO::getRemarks, reqVO.getRemarks())
//                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(OrderDO::getId));
//    }

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        IPage<OrderDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectOrderPage(page, reqVO).getRecords(), page.getTotal());
    }

}