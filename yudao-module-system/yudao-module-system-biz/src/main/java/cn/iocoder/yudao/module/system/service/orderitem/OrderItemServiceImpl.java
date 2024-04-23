package cn.iocoder.yudao.module.system.service.orderitem;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.orderitem.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.orderitem.OrderItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.orderitem.OrderItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 订单项 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public Long createOrderItem(OrderItemSaveReqVO createReqVO) {
        // 插入
        OrderItemDO orderItem = BeanUtils.toBean(createReqVO, OrderItemDO.class);
        orderItemMapper.insert(orderItem);
        // 返回
        return orderItem.getId();
    }

    @Override
    public void updateOrderItem(OrderItemSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderItemExists(updateReqVO.getId());
        // 更新
        OrderItemDO updateObj = BeanUtils.toBean(updateReqVO, OrderItemDO.class);
        orderItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderItem(Long id) {
        // 校验存在
        validateOrderItemExists(id);
        // 删除
        orderItemMapper.deleteById(id);
    }

    private void validateOrderItemExists(Long id) {
        if (orderItemMapper.selectById(id) == null) {
            throw exception(SELL_ORDER_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public OrderItemDO getOrderItem(Long id) {
        return orderItemMapper.selectById(id);
    }

    @Override
    public PageResult<OrderItemDO> getOrderItemPage(OrderItemPageReqVO pageReqVO) {
        return orderItemMapper.selectPage(pageReqVO);
    }

}