package cn.iocoder.yudao.module.system.service.commodity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.commodity.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.commodity.CommodityDO;
import cn.iocoder.yudao.module.system.dal.mysql.commodity.CommodityMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link CommodityServiceImpl} 的单元测试类
 *
 * @author Begonia
 */
@Import(CommodityServiceImpl.class)
public class CommodityServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CommodityServiceImpl commodityService;

    @Resource
    private CommodityMapper commodityMapper;

    @Test
    public void testCreateCommodity_success() {
        // 准备参数
        CommoditySaveReqVO createReqVO = randomPojo(CommoditySaveReqVO.class).setId(null);

        // 调用
        Long commodityId = commodityService.createCommodity(createReqVO);
        // 断言
        assertNotNull(commodityId);
        // 校验记录的属性是否正确
        CommodityDO commodity = commodityMapper.selectById(commodityId);
        assertPojoEquals(createReqVO, commodity, "id");
    }

    @Test
    public void testUpdateCommodity_success() {
        // mock 数据
        CommodityDO dbCommodity = randomPojo(CommodityDO.class);
        commodityMapper.insert(dbCommodity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CommoditySaveReqVO updateReqVO = randomPojo(CommoditySaveReqVO.class, o -> {
            o.setId(dbCommodity.getId()); // 设置更新的 ID
        });

        // 调用
        commodityService.updateCommodity(updateReqVO);
        // 校验是否更新正确
        CommodityDO commodity = commodityMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, commodity);
    }

    @Test
    public void testUpdateCommodity_notExists() {
        // 准备参数
        CommoditySaveReqVO updateReqVO = randomPojo(CommoditySaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> commodityService.updateCommodity(updateReqVO), COMMODITY_NOT_EXISTS);
    }

    @Test
    public void testDeleteCommodity_success() {
        // mock 数据
        CommodityDO dbCommodity = randomPojo(CommodityDO.class);
        commodityMapper.insert(dbCommodity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCommodity.getId();

        // 调用
        commodityService.deleteCommodity(id);
       // 校验数据不存在了
       assertNull(commodityMapper.selectById(id));
    }

    @Test
    public void testDeleteCommodity_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> commodityService.deleteCommodity(id), COMMODITY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCommodityPage() {
       // mock 数据
       CommodityDO dbCommodity = randomPojo(CommodityDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       commodityMapper.insert(dbCommodity);
       // 测试 name 不匹配
       commodityMapper.insert(cloneIgnoreId(dbCommodity, o -> o.setName(null)));
       // 测试 status 不匹配
       commodityMapper.insert(cloneIgnoreId(dbCommodity, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       commodityMapper.insert(cloneIgnoreId(dbCommodity, o -> o.setCreateTime(null)));
       // 准备参数
       CommodityPageReqVO reqVO = new CommodityPageReqVO();
       reqVO.setName(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CommodityDO> pageResult = commodityService.getCommodityPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCommodity, pageResult.getList().get(0));
    }

}