package cn.margin.jz.module.crm.dal.mysql.followup;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.mybatis.core.mapper.BaseMapperX;
import cn.margin.jz.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.margin.jz.module.crm.controller.admin.followup.vo.CrmFollowUpRecordPageReqVO;
import cn.margin.jz.module.crm.dal.dataobject.followup.CrmFollowUpRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 跟进记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CrmFollowUpRecordMapper extends BaseMapperX<CrmFollowUpRecordDO> {

    default PageResult<CrmFollowUpRecordDO> selectPage(CrmFollowUpRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmFollowUpRecordDO>()
                .eqIfPresent(CrmFollowUpRecordDO::getBizType, reqVO.getBizType())
                .eqIfPresent(CrmFollowUpRecordDO::getBizId, reqVO.getBizId())
                .orderByDesc(CrmFollowUpRecordDO::getId));
    }

}