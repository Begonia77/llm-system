package cn.iocoder.yudao.module.system.dal.mysql.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.order.vo.*;

/**
 * 订单 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface OrderMapper extends BaseMapperX<OrderDO> {

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getOrderNumber, reqVO.getOrderNumber())
                .eqIfPresent(OrderDO::getType, reqVO.getType())
                .eqIfPresent(OrderDO::getRemarks, reqVO.getRemarks())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderDO::getId));
    }

}