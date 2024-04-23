package cn.iocoder.yudao.module.system.service.supplier;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.supplier.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.supplier.SupplierDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 供应商 Service 接口
 *
 * @author Begonia
 */
public interface SupplierService {

    /**
     * 创建供应商
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSupplier(@Valid SupplierSaveReqVO createReqVO);

    /**
     * 更新供应商
     *
     * @param updateReqVO 更新信息
     */
    void updateSupplier(@Valid SupplierSaveReqVO updateReqVO);

    /**
     * 删除供应商
     *
     * @param id 编号
     */
    void deleteSupplier(Long id);

    /**
     * 获得供应商
     *
     * @param id 编号
     * @return 供应商
     */
    SupplierDO getSupplier(Long id);

    /**
     * 获得供应商分页
     *
     * @param pageReqVO 分页查询
     * @return 供应商分页
     */
    PageResult<SupplierDO> getSupplierPage(SupplierPageReqVO pageReqVO);

    /**
     * 获得所有供应商列表
     *
     * @return 供应商列表
     */
    List<SupplierDO> getAllSupplierList();

}