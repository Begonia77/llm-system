package cn.iocoder.yudao.module.system.service.sellOrder;

import cn.iocoder.yudao.module.system.controller.admin.sellOrderItem.vo.SellOrderItemSaveReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.system.controller.admin.sellOrder.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sellOrder.SellOrderDO;
import cn.iocoder.yudao.module.system.dal.dataobject.sellOrderItem.SellOrderItemDO;
import cn.iocoder.yudao.module.system.dal.dataobject.warehouse.WarehouseDO;
import cn.iocoder.yudao.module.system.dal.mysql.sellOrder.SellOrderMapper;
import cn.iocoder.yudao.module.system.dal.mysql.sellOrderItem.SellOrderItemMapper;
import cn.iocoder.yudao.module.system.dal.mysql.warehouse.WarehouseMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 订单 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class SellOrderServiceImpl implements SellOrderService {

    @Resource
    private SellOrderMapper sellOrderMapper;
    @Resource
    private WarehouseMapper warehouseMapper;

    @Resource
    private SellOrderItemMapper sellOrderItemMapper;

    @Override
    public Long createSellOrder(SellOrderSaveReqVO createReqVO) {
        int totalInventory = createReqVO.getSellOrderItems().stream().mapToInt(SellOrderItemSaveReqVO::getQuantity).sum();
        WarehouseDO warehouse = warehouseMapper.selectById(createReqVO.getWarehouseId());
        int currentInventory = warehouse.getInventory();

        if (totalInventory > currentInventory) {
            throw exception(WAREHOUSE_INVENTORY_NOT_ENOUGH);
        }

        // 插入
        SellOrderDO sellOrder = BeanUtils.toBean(createReqVO, SellOrderDO.class);
        sellOrderMapper.insert(sellOrder);
        sellOrder.setOrderNumber(sellOrder.getId() * 36 + 10000000);
        sellOrderMapper.updateById(sellOrder);

        for (SellOrderItemSaveReqVO sellOrderItemDO : createReqVO.getSellOrderItems()) {
            // 设置所属订单id
            sellOrderItemDO.setSellOrderId(sellOrder.getId());
            // 插入
            SellOrderItemDO sellOrderItem = BeanUtils.toBean(sellOrderItemDO, SellOrderItemDO.class);
            sellOrderItemMapper.insert(sellOrderItem);
        }
        // 返回
        return sellOrder.getId();
    }

    @Override
    public void updateSellOrder(SellOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateSellOrderExists(updateReqVO.getId());
        // 更新
        SellOrderDO updateObj = BeanUtils.toBean(updateReqVO, SellOrderDO.class);
        sellOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteSellOrder(Long id) {
        // 在删除订单之前，先删除订单项
        sellOrderItemMapper.deleteBySellOrderId(id);
        // 校验存在
        validateSellOrderExists(id);
        // 删除
        sellOrderMapper.deleteById(id);
    }

    private void validateSellOrderExists(Long id) {
        if (sellOrderMapper.selectById(id) == null) {
            throw exception(SELL_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public SellOrderDO getSellOrder(Long id) {
        return sellOrderMapper.selectById(id);
    }

    @Override
    public PageResult<SellOrderDO> getSellOrderPage(SellOrderPageReqVO pageReqVO) {
        return sellOrderMapper.selectPage(pageReqVO);
    }

}