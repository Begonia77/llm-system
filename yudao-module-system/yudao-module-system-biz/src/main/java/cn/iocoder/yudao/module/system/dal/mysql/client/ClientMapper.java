package cn.iocoder.yudao.module.system.dal.mysql.client;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.client.ClientDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.client.vo.*;

/**
 * 供应商/客户 Mapper
 *
 * @author Begonia
 */
@Mapper
public interface ClientMapper extends BaseMapperX<ClientDO> {

    default PageResult<ClientDO> selectPage(ClientPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClientDO>()
                .likeIfPresent(ClientDO::getName, reqVO.getName())
                .likeIfPresent(ClientDO::getMobile, reqVO.getMobile())
                .likeIfPresent(ClientDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(ClientDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ClientDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClientDO::getId));
    }

}