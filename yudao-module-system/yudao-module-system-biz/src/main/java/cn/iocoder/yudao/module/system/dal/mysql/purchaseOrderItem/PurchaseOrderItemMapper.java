package cn.iocoder.yudao.module.system.dal.mysql.purchaseOrderItem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrderItem.PurchaseOrderItemDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.system.controller.admin.purchaseOrderItem.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * 订单项 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface PurchaseOrderItemMapper extends BaseMapperX<PurchaseOrderItemDO> {

//    default PageResult<PurchaseOrderItemDO> selectPage(PurchaseOrderItemPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<PurchaseOrderItemDO>()
//                .eq(PurchaseOrderItemDO::getPurchaseOrderId, reqVO.getPurchaseOrderId())
//                .purchaseOrderByAsc(PurchaseOrderItemDO::getId));
//    }

    IPage<PurchaseOrderItemDO> selectPurchaseOrderItemPage(IPage<PurchaseOrderItemDO> page, @Param("req") PurchaseOrderItemPageReqVO reqVO);

    PurchaseOrderItemDO deleteByPurchaseOrderId(@Param("id") Long id);

    default PageResult<PurchaseOrderItemDO> selectPage(PurchaseOrderItemPageReqVO reqVO) {
        IPage<PurchaseOrderItemDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectPurchaseOrderItemPage(page, reqVO).getRecords(), page.getTotal());
    }

}