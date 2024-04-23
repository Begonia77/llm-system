package cn.iocoder.yudao.module.system.service.warehouse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.warehouse.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.warehouse.WarehouseDO;
import cn.iocoder.yudao.module.system.dal.mysql.warehouse.WarehouseMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

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
 * {@link WarehouseServiceImpl} 的单元测试类
 *
 * @author Begonia
 */
@Import(WarehouseServiceImpl.class)
public class WarehouseServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WarehouseServiceImpl warehouseService;

    @Resource
    private WarehouseMapper warehouseMapper;

    @Test
    public void testCreateWarehouse_success() {
        // 准备参数
        WarehouseSaveReqVO createReqVO = randomPojo(WarehouseSaveReqVO.class).setId(null);

        // 调用
        Long warehouseId = warehouseService.createWarehouse(createReqVO);
        // 断言
        assertNotNull(warehouseId);
        // 校验记录的属性是否正确
        WarehouseDO warehouse = warehouseMapper.selectById(warehouseId);
        assertPojoEquals(createReqVO, warehouse, "id");
    }

    @Test
    public void testUpdateWarehouse_success() {
        // mock 数据
        WarehouseDO dbWarehouse = randomPojo(WarehouseDO.class);
        warehouseMapper.insert(dbWarehouse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WarehouseSaveReqVO updateReqVO = randomPojo(WarehouseSaveReqVO.class, o -> {
            o.setId(dbWarehouse.getId()); // 设置更新的 ID
        });

        // 调用
        warehouseService.updateWarehouse(updateReqVO);
        // 校验是否更新正确
        WarehouseDO warehouse = warehouseMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, warehouse);
    }

    @Test
    public void testUpdateWarehouse_notExists() {
        // 准备参数
        WarehouseSaveReqVO updateReqVO = randomPojo(WarehouseSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> warehouseService.updateWarehouse(updateReqVO), WAREHOUSE_NOT_EXISTS);
    }

    @Test
    public void testDeleteWarehouse_success() {
        // mock 数据
        WarehouseDO dbWarehouse = randomPojo(WarehouseDO.class);
        warehouseMapper.insert(dbWarehouse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbWarehouse.getId();

        // 调用
        warehouseService.deleteWarehouse(id);
       // 校验数据不存在了
       assertNull(warehouseMapper.selectById(id));
    }

    @Test
    public void testDeleteWarehouse_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> warehouseService.deleteWarehouse(id), WAREHOUSE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWarehousePage() {
       // mock 数据
       WarehouseDO dbWarehouse = randomPojo(WarehouseDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setType(null);
        //    o.setUserName(null);
           o.setMaxInventory(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       warehouseMapper.insert(dbWarehouse);
       // 测试 name 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setName(null)));
       // 测试 type 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setType(null)));
       // 测试 userName 不匹配
    //    warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setUserName(null)));
       // 测试 maxInventory 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setMaxInventory(null)));
       // 测试 status 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setCreateTime(null)));
       // 准备参数
       WarehousePageReqVO reqVO = new WarehousePageReqVO();
       reqVO.setName(null);
       reqVO.setType(null);
    //    reqVO.setUserName(null);
       reqVO.setMaxInventory(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<WarehouseDO> pageResult = warehouseService.getWarehousePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbWarehouse, pageResult.getList().get(0));
    }

}