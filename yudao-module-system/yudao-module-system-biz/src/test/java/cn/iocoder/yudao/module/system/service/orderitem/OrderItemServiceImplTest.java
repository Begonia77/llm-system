package cn.iocoder.yudao.module.system.service.orderitem;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.orderitem.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.orderitem.OrderItemDO;
import cn.iocoder.yudao.module.system.dal.mysql.orderitem.OrderItemMapper;
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
 * {@link OrderItemServiceImpl} 的单元测试类
 *
 * @author Begonia
 */
@Import(OrderItemServiceImpl.class)
public class OrderItemServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OrderItemServiceImpl orderItemService;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Test
    public void testCreateOrderItem_success() {
        // 准备参数
        OrderItemSaveReqVO createReqVO = randomPojo(OrderItemSaveReqVO.class).setId(null);

        // 调用
        Long orderItemId = orderItemService.createOrderItem(createReqVO);
        // 断言
        assertNotNull(orderItemId);
        // 校验记录的属性是否正确
        OrderItemDO orderItem = orderItemMapper.selectById(orderItemId);
        assertPojoEquals(createReqVO, orderItem, "id");
    }

    @Test
    public void testUpdateOrderItem_success() {
        // mock 数据
        OrderItemDO dbOrderItem = randomPojo(OrderItemDO.class);
        orderItemMapper.insert(dbOrderItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderItemSaveReqVO updateReqVO = randomPojo(OrderItemSaveReqVO.class, o -> {
            o.setId(dbOrderItem.getId()); // 设置更新的 ID
        });

        // 调用
        orderItemService.updateOrderItem(updateReqVO);
        // 校验是否更新正确
        OrderItemDO orderItem = orderItemMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, orderItem);
    }

    @Test
    public void testUpdateOrderItem_notExists() {
        // 准备参数
        OrderItemSaveReqVO updateReqVO = randomPojo(OrderItemSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderItemService.updateOrderItem(updateReqVO), SELL_ORDER_ITEM_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrderItem_success() {
        // mock 数据
        OrderItemDO dbOrderItem = randomPojo(OrderItemDO.class);
        orderItemMapper.insert(dbOrderItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOrderItem.getId();

        // 调用
        orderItemService.deleteOrderItem(id);
       // 校验数据不存在了
       assertNull(orderItemMapper.selectById(id));
    }

    @Test
    public void testDeleteOrderItem_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> orderItemService.deleteOrderItem(id), SELL_ORDER_ITEM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderItemPage() {
       // mock 数据
       OrderItemDO dbOrderItem = randomPojo(OrderItemDO.class, o -> { // 等会查询到
           o.setRemarks(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       orderItemMapper.insert(dbOrderItem);
       // 测试 remarks 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setRemarks(null)));
       // 测试 status 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setCreateTime(null)));
       // 准备参数
       OrderItemPageReqVO reqVO = new OrderItemPageReqVO();
       reqVO.setRemarks(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<OrderItemDO> pageResult = orderItemService.getOrderItemPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrderItem, pageResult.getList().get(0));
    }

}