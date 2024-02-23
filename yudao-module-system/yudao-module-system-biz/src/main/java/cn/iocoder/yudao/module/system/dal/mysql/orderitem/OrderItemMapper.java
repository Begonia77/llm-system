package cn.iocoder.yudao.module.system.dal.mysql.orderitem;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.orderitem.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.system.controller.admin.orderitem.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * 订单项 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface OrderItemMapper extends BaseMapperX<OrderItemDO> {

//    default PageResult<OrderItemDO> selectPage(OrderItemPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<OrderItemDO>()
//                .eq(OrderItemDO::getOrderId, reqVO.getOrderId())
//                .orderByAsc(OrderItemDO::getId));
//    }

    IPage<OrderItemDO> selectOrderItemPage(IPage<OrderItemDO> page, @Param("req") OrderItemPageReqVO reqVO);

    default PageResult<OrderItemDO> selectPage(OrderItemPageReqVO reqVO) {
        IPage<OrderItemDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectOrderItemPage(page, reqVO).getRecords(), page.getTotal());
    }

}