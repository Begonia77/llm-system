package cn.iocoder.yudao.module.system.service.client;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.client.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.client.ClientDO;
import cn.iocoder.yudao.module.system.dal.mysql.client.ClientMapper;
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
 * {@link ClientServiceImpl} 的单元测试类
 *
 * @author Begonia
 */
@Import(ClientServiceImpl.class)
public class ClientServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ClientServiceImpl clientService;

    @Resource
    private ClientMapper clientMapper;

    @Test
    public void testCreateClient_success() {
        // 准备参数
        ClientSaveReqVO createReqVO = randomPojo(ClientSaveReqVO.class).setId(null);

        // 调用
        Long clientId = clientService.createClient(createReqVO);
        // 断言
        assertNotNull(clientId);
        // 校验记录的属性是否正确
        ClientDO client = clientMapper.selectById(clientId);
        assertPojoEquals(createReqVO, client, "id");
    }

    @Test
    public void testUpdateClient_success() {
        // mock 数据
        ClientDO dbClient = randomPojo(ClientDO.class);
        clientMapper.insert(dbClient);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ClientSaveReqVO updateReqVO = randomPojo(ClientSaveReqVO.class, o -> {
            o.setId(dbClient.getId()); // 设置更新的 ID
        });

        // 调用
        clientService.updateClient(updateReqVO);
        // 校验是否更新正确
        ClientDO client = clientMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, client);
    }

    @Test
    public void testUpdateClient_notExists() {
        // 准备参数
        ClientSaveReqVO updateReqVO = randomPojo(ClientSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> clientService.updateClient(updateReqVO), CLIENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteClient_success() {
        // mock 数据
        ClientDO dbClient = randomPojo(ClientDO.class);
        clientMapper.insert(dbClient);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbClient.getId();

        // 调用
        clientService.deleteClient(id);
       // 校验数据不存在了
       assertNull(clientMapper.selectById(id));
    }

    @Test
    public void testDeleteClient_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> clientService.deleteClient(id), CLIENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetClientPage() {
       // mock 数据
       ClientDO dbClient = randomPojo(ClientDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setMobile(null);
           o.setCompanyName(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       clientMapper.insert(dbClient);
       // 测试 name 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setName(null)));
       // 测试 mobile 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setMobile(null)));
       // 测试 companyName 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setCompanyName(null)));
       // 测试 status 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setCreateTime(null)));
       // 准备参数
       ClientPageReqVO reqVO = new ClientPageReqVO();
       reqVO.setName(null);
       reqVO.setMobile(null);
       reqVO.setCompanyName(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ClientDO> pageResult = clientService.getClientPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbClient, pageResult.getList().get(0));
    }

}