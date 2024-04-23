package cn.iocoder.yudao.module.system.service.commodity;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.commodity.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.commodity.CommodityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.commodity.CommodityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 产品 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public Long createCommodity(CommoditySaveReqVO createReqVO) {
        // 插入
        CommodityDO commodity = BeanUtils.toBean(createReqVO, CommodityDO.class);
        commodityMapper.insert(commodity);
        // 返回
        return commodity.getId();
    }

    @Override
    public void updateCommodity(CommoditySaveReqVO updateReqVO) {
        // 校验存在
        validateCommodityExists(updateReqVO.getId());
        // 更新
        CommodityDO updateObj = BeanUtils.toBean(updateReqVO, CommodityDO.class);
        commodityMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommodity(Long id) {
        // 校验存在
        validateCommodityExists(id);
        // 删除
        commodityMapper.deleteById(id);
    }

    private void validateCommodityExists(Long id) {
        if (commodityMapper.selectById(id) == null) {
            throw exception(COMMODITY_NOT_EXISTS);
        }
    }

    @Override
    public CommodityDO getCommodity(Long id) {
        return commodityMapper.selectById(id);
    }

    @Override
    public PageResult<CommodityDO> getCommodityPage(CommodityPageReqVO pageReqVO) {
        return commodityMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CommodityDO> getAllCommodityList() {
        return commodityMapper.selectList();
    }

}