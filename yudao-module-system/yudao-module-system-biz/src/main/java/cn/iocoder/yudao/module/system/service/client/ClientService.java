package cn.iocoder.yudao.module.system.service.client;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.client.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.client.ClientDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 客户 Service 接口
 *
 * @author Begonia
 */
public interface ClientService {

    /**
     * 创建客户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createClient(@Valid ClientSaveReqVO createReqVO);

    /**
     * 更新客户
     *
     * @param updateReqVO 更新信息
     */
    void updateClient(@Valid ClientSaveReqVO updateReqVO);

    /**
     * 删除客户
     *
     * @param id 编号
     */
    void deleteClient(Long id);

    /**
     * 获得客户
     *
     * @param id 编号
     * @return 客户
     */
    ClientDO getClient(Long id);

    /**
     * 获得客户分页
     *
     * @param pageReqVO 分页查询
     * @return 客户分页
     */
    PageResult<ClientDO> getClientPage(ClientPageReqVO pageReqVO);

    /**
     * 获得所有客户列表
     *
     * @return 客户列表
     */
    List<ClientDO> getAllClientList();

}