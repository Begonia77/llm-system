package cn.iocoder.yudao.module.system.service.order;

import cn.iocoder.yudao.module.system.controller.admin.orderitem.vo.OrderItemSaveReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.module.system.dal.dataobject.orderitem.OrderItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.order.OrderMapper;
import cn.iocoder.yudao.module.system.dal.mysql.orderitem.OrderItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 订单 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public Long createOrder(OrderSaveReqVO createReqVO) {
        // 插入
        OrderDO order = BeanUtils.toBean(createReqVO, OrderDO.class);
        orderMapper.insert(order);
        for (OrderItemSaveReqVO orderItemDO : createReqVO.getOrderItems()) {
            // 设置所属订单id
            orderItemDO.setOrderId(order.getId());
            // 插入
            OrderItemDO orderItem = BeanUtils.toBean(orderItemDO, OrderItemDO.class);
            orderItemMapper.insert(orderItem);
        }
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(OrderSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        OrderDO updateObj = BeanUtils.toBean(updateReqVO, OrderDO.class);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public OrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

}