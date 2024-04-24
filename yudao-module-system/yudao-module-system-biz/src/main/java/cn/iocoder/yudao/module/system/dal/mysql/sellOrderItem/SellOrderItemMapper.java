package cn.iocoder.yudao.module.system.dal.mysql.sellOrderItem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.sellOrderItem.SellOrderItemDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.system.controller.admin.sellOrderItem.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * 订单项 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface SellOrderItemMapper extends BaseMapperX<SellOrderItemDO> {

//    default PageResult<SellOrderItemDO> selectPage(SellOrderItemPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<SellOrderItemDO>()
//                .eq(SellOrderItemDO::getSellOrderId, reqVO.getSellOrderId())
//                .sellOrderByAsc(SellOrderItemDO::getId));
//    }

    IPage<SellOrderItemDO> selectSellOrderItemPage(IPage<SellOrderItemDO> page, @Param("req") SellOrderItemPageReqVO reqVO);

    SellOrderItemDO deleteBySellOrderId(@Param("id") Long id);

    default PageResult<SellOrderItemDO> selectPage(SellOrderItemPageReqVO reqVO) {
        IPage<SellOrderItemDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectSellOrderItemPage(page, reqVO).getRecords(), page.getTotal());
    }

}