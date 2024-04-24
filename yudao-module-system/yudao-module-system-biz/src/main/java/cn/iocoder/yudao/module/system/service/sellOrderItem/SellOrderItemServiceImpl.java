package cn.iocoder.yudao.module.system.service.sellOrderItem;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.sellOrderItem.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.sellOrderItem.SellOrderItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.sellOrderItem.SellOrderItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 订单项 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class SellOrderItemServiceImpl implements SellOrderItemService {

    @Resource
    private SellOrderItemMapper sellOrderItemMapper;

    @Override
    public Long createSellOrderItem(SellOrderItemSaveReqVO createReqVO) {
        // 插入
        SellOrderItemDO sellOrderItem = BeanUtils.toBean(createReqVO, SellOrderItemDO.class);
        sellOrderItemMapper.insert(sellOrderItem);
        // 返回
        return sellOrderItem.getId();
    }

    @Override
    public void updateSellOrderItem(SellOrderItemSaveReqVO updateReqVO) {
        // 校验存在
        validateSellOrderItemExists(updateReqVO.getId());
        // 更新
        SellOrderItemDO updateObj = BeanUtils.toBean(updateReqVO, SellOrderItemDO.class);
        sellOrderItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteSellOrderItem(Long id) {
        // 校验存在
        validateSellOrderItemExists(id);
        // 删除
        sellOrderItemMapper.deleteById(id);
    }

    private void validateSellOrderItemExists(Long id) {
        if (sellOrderItemMapper.selectById(id) == null) {
            throw exception(SELL_ORDER_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public SellOrderItemDO getSellOrderItem(Long id) {
        return sellOrderItemMapper.selectById(id);
    }

    @Override
    public PageResult<SellOrderItemDO> getSellOrderItemPage(SellOrderItemPageReqVO pageReqVO) {
        return sellOrderItemMapper.selectPage(pageReqVO);
    }

}