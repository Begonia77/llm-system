package cn.iocoder.yudao.module.system.service.brand;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.brand.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.brand.BrandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 品牌 Service 接口
 *
 * @author Begonia
 */
public interface BrandService {

    /**
     * 创建品牌
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBrand(@Valid BrandSaveReqVO createReqVO);

    /**
     * 更新品牌
     *
     * @param updateReqVO 更新信息
     */
    void updateBrand(@Valid BrandSaveReqVO updateReqVO);

    /**
     * 删除品牌
     *
     * @param id 编号
     */
    void deleteBrand(Long id);

    /**
     * 获得品牌
     *
     * @param id 编号
     * @return 品牌
     */
    BrandDO getBrand(Long id);

    /**
     * 获得品牌分页
     *
     * @param pageReqVO 分页查询
     * @return 品牌分页
     */
    PageResult<BrandDO> getBrandPage(BrandPageReqVO pageReqVO);

    /**
     * 获得所有品牌列表
     *
     * @return 品牌列表
     */
    List<BrandDO> getAllBrandList();

}