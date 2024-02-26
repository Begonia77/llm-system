package cn.iocoder.yudao.module.system.dal.mysql.warehouse;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.warehouse.WarehouseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.system.controller.admin.warehouse.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * 仓库 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface WarehouseMapper extends BaseMapperX<WarehouseDO> {

//    default PageResult<WarehouseDO> selectPage(WarehousePageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<WarehouseDO>()
//                .likeIfPresent(WarehouseDO::getName, reqVO.getName())
//                .eqIfPresent(WarehouseDO::getType, reqVO.getType())
//                .eqIfPresent(WarehouseDO::getMaxInventory, reqVO.getMaxInventory())
//                .eqIfPresent(WarehouseDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(WarehouseDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(WarehouseDO::getId));
//    }
    IPage<WarehouseDO> selectWarehousePage(IPage<WarehouseDO> page, @Param("req") WarehousePageReqVO reqVO);

    WarehouseDO selectById(@Param("id") Long id);

    default PageResult<WarehouseDO> selectPage(WarehousePageReqVO reqVO) {
        IPage<WarehouseDO> page = MyBatisUtils.buildPage(reqVO);
        return new PageResult<>(selectWarehousePage(page, reqVO).getRecords(), page.getTotal());
    }

}