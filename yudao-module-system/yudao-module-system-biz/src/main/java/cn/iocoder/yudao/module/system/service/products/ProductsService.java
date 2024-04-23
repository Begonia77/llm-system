package cn.iocoder.yudao.module.system.service.products;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.products.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.products.ProductsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 产品 Service 接口
 *
 * @author Begonia
 */
public interface ProductsService {

    /**
     * 创建产品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProducts(@Valid ProductsSaveReqVO createReqVO);

    /**
     * 更新产品
     *
     * @param updateReqVO 更新信息
     */
    void updateProducts(@Valid ProductsSaveReqVO updateReqVO);

    /**
     * 删除产品
     *
     * @param id 编号
     */
    void deleteProducts(Long id);

    /**
     * 获得产品
     *
     * @param id 编号
     * @return 产品
     */
    ProductsDO getProducts(Long id);

    /**
     * 获得产品分页
     *
     * @param pageReqVO 分页查询
     * @return 产品分页
     */
    PageResult<ProductsDO> getProductsPage(ProductsPageReqVO pageReqVO);

    /**
     * 获得所有产品列表
     *
     * @return 产品列表
     */
    List<ProductsDO> getAllProductsList();

}