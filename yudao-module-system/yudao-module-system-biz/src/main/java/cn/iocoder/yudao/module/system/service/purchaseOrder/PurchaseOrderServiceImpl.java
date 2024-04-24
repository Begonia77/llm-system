package cn.iocoder.yudao.module.system.service.purchaseOrder;

import cn.iocoder.yudao.module.system.controller.admin.purchaseOrderItem.vo.PurchaseOrderItemSaveReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.system.controller.admin.purchaseOrder.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrder.PurchaseOrderDO;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrderItem.PurchaseOrderItemDO;
import cn.iocoder.yudao.module.system.dal.dataobject.warehouse.WarehouseDO;
import cn.iocoder.yudao.module.system.dal.mysql.warehouse.WarehouseMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.purchaseOrder.PurchaseOrderMapper;
import cn.iocoder.yudao.module.system.dal.mysql.purchaseOrderItem.PurchaseOrderItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 订单 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;
    @Resource
    private WarehouseMapper warehouseMapper;

    @Resource
    private PurchaseOrderItemMapper purchaseOrderItemMapper;

    @Override
    public Long createPurchaseOrder(PurchaseOrderSaveReqVO createReqVO) {
        int totalInventory = createReqVO.getPurchaseOrderItems().stream().mapToInt(PurchaseOrderItemSaveReqVO::getQuantity).sum();
        WarehouseDO warehouse = warehouseMapper.selectById(createReqVO.getWarehouseId());
        int currentInventory = warehouse.getInventory();

        if (totalInventory + currentInventory > warehouse.getMaxInventory()) {
            throw exception(WAREHOUSE_INVENTORY_EXCEED_MAX);
        }

        // 插入
        PurchaseOrderDO purchaseOrder = BeanUtils.toBean(createReqVO, PurchaseOrderDO.class);
        purchaseOrderMapper.insert(purchaseOrder);
        purchaseOrder.setOrderNumber(purchaseOrder.getId() * 36 + 10000000);
        purchaseOrderMapper.updateById(purchaseOrder);

        for (PurchaseOrderItemSaveReqVO purchaseOrderItemDO : createReqVO.getPurchaseOrderItems()) {
            // 设置所属订单id
            purchaseOrderItemDO.setPurchaseOrderId(purchaseOrder.getId());
            // 插入
            PurchaseOrderItemDO purchaseOrderItem = BeanUtils.toBean(purchaseOrderItemDO, PurchaseOrderItemDO.class);
            purchaseOrderItemMapper.insert(purchaseOrderItem);
        }
        // 返回
        return purchaseOrder.getId();
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrderSaveReqVO updateReqVO) {
        // 校验存在
        validatePurchaseOrderExists(updateReqVO.getId());
        // 更新
        PurchaseOrderDO updateObj = BeanUtils.toBean(updateReqVO, PurchaseOrderDO.class);
        purchaseOrderMapper.updateById(updateObj);
    }

    @Override
    public void deletePurchaseOrder(Long id) {
        // 在删除订单之前，先删除订单项
        purchaseOrderItemMapper.deleteByPurchaseOrderId(id);
        // 校验存在
        validatePurchaseOrderExists(id);
        // 删除
        purchaseOrderMapper.deleteById(id);
    }

    private void validatePurchaseOrderExists(Long id) {
        if (purchaseOrderMapper.selectById(id) == null) {
            throw exception(SELL_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public PurchaseOrderDO getPurchaseOrder(Long id) {
        return purchaseOrderMapper.selectById(id);
    }

    @Override
    public PageResult<PurchaseOrderDO> getPurchaseOrderPage(PurchaseOrderPageReqVO pageReqVO) {
        return purchaseOrderMapper.selectPage(pageReqVO);
    }

}