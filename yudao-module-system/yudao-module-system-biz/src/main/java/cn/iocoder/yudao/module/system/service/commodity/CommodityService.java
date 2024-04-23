package cn.iocoder.yudao.module.system.service.commodity;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.commodity.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.commodity.CommodityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 产品 Service 接口
 *
 * @author Begonia
 */
public interface CommodityService {

    /**
     * 创建产品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommodity(@Valid CommoditySaveReqVO createReqVO);

    /**
     * 更新产品
     *
     * @param updateReqVO 更新信息
     */
    void updateCommodity(@Valid CommoditySaveReqVO updateReqVO);

    /**
     * 删除产品
     *
     * @param id 编号
     */
    void deleteCommodity(Long id);

    /**
     * 获得产品
     *
     * @param id 编号
     * @return 产品
     */
    CommodityDO getCommodity(Long id);

    /**
     * 获得产品分页
     *
     * @param pageReqVO 分页查询
     * @return 产品分页
     */
    PageResult<CommodityDO> getCommodityPage(CommodityPageReqVO pageReqVO);

    /**
     * 获得所有产品列表
     *
     * @return 产品列表
     */
    List<CommodityDO> getAllCommodityList();

}