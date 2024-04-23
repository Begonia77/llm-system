package cn.iocoder.yudao.module.system.service.warehouse;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.warehouse.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.warehouse.WarehouseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 仓库 Service 接口
 *
 * @author Begonia
 */
public interface WarehouseService {

    /**
     * 创建仓库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWarehouse(@Valid WarehouseSaveReqVO createReqVO);

    /**
     * 更新仓库
     *
     * @param updateReqVO 更新信息
     */
    void updateWarehouse(@Valid WarehouseSaveReqVO updateReqVO);

    /**
     * 删除仓库
     *
     * @param id 编号
     */
    void deleteWarehouse(Long id);

    /**
     * 获得仓库
     *
     * @param id 编号
     * @return 仓库
     */
    WarehouseDO getWarehouse(Long id);

    /**
     * 获得仓库分页
     *
     * @param pageReqVO 分页查询
     * @return 仓库分页
     */
    PageResult<WarehouseDO> getWarehousePage(WarehousePageReqVO pageReqVO);

    /**
     * 获得所有仓库列表
     *
     * @return 仓库列表
     */
    List<WarehouseDO> getAllWarehouseList();

}