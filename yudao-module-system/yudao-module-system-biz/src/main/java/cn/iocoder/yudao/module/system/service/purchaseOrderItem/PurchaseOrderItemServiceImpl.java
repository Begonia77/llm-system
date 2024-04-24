package cn.iocoder.yudao.module.system.service.purchaseOrderItem;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.purchaseOrderItem.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrderItem.PurchaseOrderItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.purchaseOrderItem.PurchaseOrderItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 订单项 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {

    @Resource
    private PurchaseOrderItemMapper purchaseOrderItemMapper;

    @Override
    public Long createPurchaseOrderItem(PurchaseOrderItemSaveReqVO createReqVO) {
        // 插入
        PurchaseOrderItemDO purchaseOrderItem = BeanUtils.toBean(createReqVO, PurchaseOrderItemDO.class);
        purchaseOrderItemMapper.insert(purchaseOrderItem);
        // 返回
        return purchaseOrderItem.getId();
    }

    @Override
    public void updatePurchaseOrderItem(PurchaseOrderItemSaveReqVO updateReqVO) {
        // 校验存在
        validatePurchaseOrderItemExists(updateReqVO.getId());
        // 更新
        PurchaseOrderItemDO updateObj = BeanUtils.toBean(updateReqVO, PurchaseOrderItemDO.class);
        purchaseOrderItemMapper.updateById(updateObj);
    }

    @Override
    public void deletePurchaseOrderItem(Long id) {
        // 校验存在
        validatePurchaseOrderItemExists(id);
        // 删除
        purchaseOrderItemMapper.deleteById(id);
    }

    private void validatePurchaseOrderItemExists(Long id) {
        if (purchaseOrderItemMapper.selectById(id) == null) {
            throw exception(SELL_ORDER_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public PurchaseOrderItemDO getPurchaseOrderItem(Long id) {
        return purchaseOrderItemMapper.selectById(id);
    }

    @Override
    public PageResult<PurchaseOrderItemDO> getPurchaseOrderItemPage(PurchaseOrderItemPageReqVO pageReqVO) {
        return purchaseOrderItemMapper.selectPage(pageReqVO);
    }

}