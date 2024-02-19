package cn.iocoder.yudao.module.system.service.order;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 订单 Service 接口
 *
 * @author Begonia
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid OrderSaveReqVO createReqVO);

    /**
     * 更新订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid OrderSaveReqVO updateReqVO);

    /**
     * 删除订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得订单
     *
     * @param id 编号
     * @return 订单
     */
    OrderDO getOrder(Long id);

    /**
     * 获得订单分页
     *
     * @param pageReqVO 分页查询
     * @return 订单分页
     */
    PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO);

}