package cn.iocoder.yudao.module.system.dal.mysql.purchaseOrder;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.system.dal.dataobject.purchaseOrder.PurchaseOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.purchaseOrder.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 订单 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface PurchaseOrderMapper extends BaseMapperX<PurchaseOrderDO> {
    IPage<PurchaseOrderDO> selectPurchaseOrderPage(IPage<PurchaseOrderDO> page, @Param("req") PurchaseOrderPageReqVO reqVO);

    default PageResult<PurchaseOrderDO> selectPage(PurchaseOrderPageReqVO reqVO) {
        IPage<PurchaseOrderDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectPurchaseOrderPage(page, reqVO).getRecords(), page.getTotal());
    }

}