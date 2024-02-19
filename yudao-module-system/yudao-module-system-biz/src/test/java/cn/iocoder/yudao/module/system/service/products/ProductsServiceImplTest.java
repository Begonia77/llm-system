package cn.iocoder.yudao.module.system.service.products;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.products.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.products.ProductsDO;
import cn.iocoder.yudao.module.system.dal.mysql.products.ProductsMapper;
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
 * {@link ProductsServiceImpl} 的单元测试类
 *
 * @author Begonia
 */
@Import(ProductsServiceImpl.class)
public class ProductsServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProductsServiceImpl productsService;

    @Resource
    private ProductsMapper productsMapper;

    @Test
    public void testCreateProducts_success() {
        // 准备参数
        ProductsSaveReqVO createReqVO = randomPojo(ProductsSaveReqVO.class).setId(null);

        // 调用
        Long productsId = productsService.createProducts(createReqVO);
        // 断言
        assertNotNull(productsId);
        // 校验记录的属性是否正确
        ProductsDO products = productsMapper.selectById(productsId);
        assertPojoEquals(createReqVO, products, "id");
    }

    @Test
    public void testUpdateProducts_success() {
        // mock 数据
        ProductsDO dbProducts = randomPojo(ProductsDO.class);
        productsMapper.insert(dbProducts);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProductsSaveReqVO updateReqVO = randomPojo(ProductsSaveReqVO.class, o -> {
            o.setId(dbProducts.getId()); // 设置更新的 ID
        });

        // 调用
        productsService.updateProducts(updateReqVO);
        // 校验是否更新正确
        ProductsDO products = productsMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, products);
    }

    @Test
    public void testUpdateProducts_notExists() {
        // 准备参数
        ProductsSaveReqVO updateReqVO = randomPojo(ProductsSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> productsService.updateProducts(updateReqVO), PRODUCTS_NOT_EXISTS);
    }

    @Test
    public void testDeleteProducts_success() {
        // mock 数据
        ProductsDO dbProducts = randomPojo(ProductsDO.class);
        productsMapper.insert(dbProducts);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProducts.getId();

        // 调用
        productsService.deleteProducts(id);
       // 校验数据不存在了
       assertNull(productsMapper.selectById(id));
    }

    @Test
    public void testDeleteProducts_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> productsService.deleteProducts(id), PRODUCTS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductsPage() {
       // mock 数据
       ProductsDO dbProducts = randomPojo(ProductsDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setCategory(null);
           o.setBrand(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       productsMapper.insert(dbProducts);
       // 测试 name 不匹配
       productsMapper.insert(cloneIgnoreId(dbProducts, o -> o.setName(null)));
       // 测试 category 不匹配
       productsMapper.insert(cloneIgnoreId(dbProducts, o -> o.setCategory(null)));
       // 测试 brand 不匹配
       productsMapper.insert(cloneIgnoreId(dbProducts, o -> o.setBrand(null)));
       // 测试 status 不匹配
       productsMapper.insert(cloneIgnoreId(dbProducts, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       productsMapper.insert(cloneIgnoreId(dbProducts, o -> o.setCreateTime(null)));
       // 准备参数
       ProductsPageReqVO reqVO = new ProductsPageReqVO();
       reqVO.setName(null);
       reqVO.setCategory(null);
       reqVO.setBrand(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ProductsDO> pageResult = productsService.getProductsPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProducts, pageResult.getList().get(0));
    }

}