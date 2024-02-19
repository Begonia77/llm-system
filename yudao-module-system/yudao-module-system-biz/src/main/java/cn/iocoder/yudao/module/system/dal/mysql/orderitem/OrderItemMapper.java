package cn.iocoder.yudao.module.system.dal.mysql.orderitem;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.orderitem.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.orderitem.vo.*;

/**
 * 订单项 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface OrderItemMapper extends BaseMapperX<OrderItemDO> {

    default PageResult<OrderItemDO> selectPage(OrderItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderItemDO>()
                .eqIfPresent(OrderItemDO::getRemarks, reqVO.getRemarks())
                .eqIfPresent(OrderItemDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(OrderItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderItemDO::getId));
    }

}