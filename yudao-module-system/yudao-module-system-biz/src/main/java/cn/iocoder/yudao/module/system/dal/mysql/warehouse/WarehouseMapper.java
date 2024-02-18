package cn.iocoder.yudao.module.system.dal.mysql.warehouse;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.warehouse.WarehouseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.warehouse.vo.*;

/**
 * 仓库 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface WarehouseMapper extends BaseMapperX<WarehouseDO> {

    default PageResult<WarehouseDO> selectPage(WarehousePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WarehouseDO>()
                .likeIfPresent(WarehouseDO::getName, reqVO.getName())
                .eqIfPresent(WarehouseDO::getType, reqVO.getType())
                .likeIfPresent(WarehouseDO::getUserName, reqVO.getUserName())
                .eqIfPresent(WarehouseDO::getMaxInventory, reqVO.getMaxInventory())
                .eqIfPresent(WarehouseDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(WarehouseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WarehouseDO::getId));
    }

}