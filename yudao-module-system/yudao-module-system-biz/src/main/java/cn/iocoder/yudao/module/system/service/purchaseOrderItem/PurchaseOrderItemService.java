package cn.iocoder.yudao.module.system.service.purchaseOrderItem;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.purchaseOrderItem.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrderItem.PurchaseOrderItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 订单项 Service 接口
 *
 * @author Begonia
 */
public interface PurchaseOrderItemService {

    /**
     * 创建订单项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPurchaseOrderItem(@Valid PurchaseOrderItemSaveReqVO createReqVO);

    /**
     * 更新订单项
     *
     * @param updateReqVO 更新信息
     */
    void updatePurchaseOrderItem(@Valid PurchaseOrderItemSaveReqVO updateReqVO);

    /**
     * 删除订单项
     *
     * @param id 编号
     */
    void deletePurchaseOrderItem(Long id);

    /**
     * 获得订单项
     *
     * @param id 编号
     * @return 订单项
     */
    PurchaseOrderItemDO getPurchaseOrderItem(Long id);

    /**
     * 获得订单项分页
     *
     * @param pageReqVO 分页查询
     * @return 订单项分页
     */
    PageResult<PurchaseOrderItemDO> getPurchaseOrderItemPage(PurchaseOrderItemPageReqVO pageReqVO);

}