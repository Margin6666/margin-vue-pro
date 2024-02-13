package cn.margin.jz.module.mp.dal.mysql.user;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.mybatis.core.mapper.BaseMapperX;
import cn.margin.jz.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.margin.jz.module.mp.controller.admin.user.vo.MpUserPageReqVO;
import cn.margin.jz.module.mp.dal.dataobject.user.MpUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MpUserMapper extends BaseMapperX<MpUserDO> {

    default PageResult<MpUserDO> selectPage(MpUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MpUserDO>()
                .likeIfPresent(MpUserDO::getOpenid, reqVO.getOpenid())
                .likeIfPresent(MpUserDO::getNickname, reqVO.getNickname())
                .eqIfPresent(MpUserDO::getAccountId, reqVO.getAccountId())
                .orderByDesc(MpUserDO::getId));
    }

    default MpUserDO selectByAppIdAndOpenid(String appId, String openid) {
        return selectOne(MpUserDO::getAppId, appId,
                MpUserDO::getOpenid, openid);
    }

    default List<MpUserDO> selectListByAppIdAndOpenid(String appId, List<String> openids) {
        return selectList(new LambdaQueryWrapperX<MpUserDO>()
                .eq(MpUserDO::getAppId, appId)
                .in(MpUserDO::getOpenid, openids));

    }

}
