package cn.iocoder.yudao.module.system.dal.mysql.sellOrder;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.system.dal.dataobject.sellOrder.SellOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.sellOrder.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 订单 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface SellOrderMapper extends BaseMapperX<SellOrderDO> {
    IPage<SellOrderDO> selectSellOrderPage(IPage<SellOrderDO> page, @Param("req") SellOrderPageReqVO reqVO);

    default PageResult<SellOrderDO> selectPage(SellOrderPageReqVO reqVO) {
        IPage<SellOrderDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectSellOrderPage(page, reqVO).getRecords(), page.getTotal());
    }

}