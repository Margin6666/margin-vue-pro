package cn.margin.jz.module.mp.dal.mysql.material;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.mybatis.core.mapper.BaseMapperX;
import cn.margin.jz.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.margin.jz.module.mp.controller.admin.material.vo.MpMaterialPageReqVO;
import cn.margin.jz.module.mp.dal.dataobject.material.MpMaterialDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface MpMaterialMapper extends BaseMapperX<MpMaterialDO> {

    default MpMaterialDO selectByAccountIdAndMediaId(Long accountId, String mediaId) {
        return selectOne(MpMaterialDO::getAccountId, accountId,
                MpMaterialDO::getMediaId, mediaId);
    }

    default PageResult<MpMaterialDO> selectPage(MpMaterialPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MpMaterialDO>()
                .eq(MpMaterialDO::getAccountId, pageReqVO.getAccountId())
                .eqIfPresent(MpMaterialDO::getPermanent, pageReqVO.getPermanent())
                .eqIfPresent(MpMaterialDO::getType, pageReqVO.getType())
                .orderByDesc(MpMaterialDO::getId));
    }

    default List<MpMaterialDO> selectListByMediaId(Collection<String> mediaIds) {
        return selectList(MpMaterialDO::getMediaId, mediaIds);
    }

}
