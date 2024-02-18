package cn.iocoder.yudao.module.system.service.client;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.client.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.client.ClientDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.client.ClientMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 供应商/客户 Service 实现类
 *
 * @author Begonia
 */
@Service
@Validated
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientMapper clientMapper;

    @Override
    public Long createClient(ClientSaveReqVO createReqVO) {
        // 插入
        ClientDO client = BeanUtils.toBean(createReqVO, ClientDO.class);
        clientMapper.insert(client);
        // 返回
        return client.getId();
    }

    @Override
    public void updateClient(ClientSaveReqVO updateReqVO) {
        // 校验存在
        validateClientExists(updateReqVO.getId());
        // 更新
        ClientDO updateObj = BeanUtils.toBean(updateReqVO, ClientDO.class);
        clientMapper.updateById(updateObj);
    }

    @Override
    public void deleteClient(Long id) {
        // 校验存在
        validateClientExists(id);
        // 删除
        clientMapper.deleteById(id);
    }

    private void validateClientExists(Long id) {
        if (clientMapper.selectById(id) == null) {
            throw exception(CLIENT_NOT_EXISTS);
        }
    }

    @Override
    public ClientDO getClient(Long id) {
        return clientMapper.selectById(id);
    }

    @Override
    public PageResult<ClientDO> getClientPage(ClientPageReqVO pageReqVO) {
        return clientMapper.selectPage(pageReqVO);
    }

}