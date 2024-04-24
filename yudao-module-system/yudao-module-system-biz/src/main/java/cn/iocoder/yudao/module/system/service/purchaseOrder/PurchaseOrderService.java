package cn.iocoder.yudao.module.system.service.purchaseOrder;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.purchaseOrder.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrder.PurchaseOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 订单 Service 接口
 *
 * @author Begonia
 */
public interface PurchaseOrderService {

    /**
     * 创建订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPurchaseOrder(@Valid PurchaseOrderSaveReqVO createReqVO);

    /**
     * 更新订单
     *
     * @param updateReqVO 更新信息
     */
    void updatePurchaseOrder(@Valid PurchaseOrderSaveReqVO updateReqVO);

    /**
     * 删除订单
     *
     * @param id 编号
     */
    void deletePurchaseOrder(Long id);

    /**
     * 获得订单
     *
     * @param id 编号
     * @return 订单
     */
    PurchaseOrderDO getPurchaseOrder(Long id);

    /**
     * 获得订单分页
     *
     * @param pageReqVO 分页查询
     * @return 订单分页
     */
    PageResult<PurchaseOrderDO> getPurchaseOrderPage(PurchaseOrderPageReqVO pageReqVO);

}