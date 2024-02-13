package cn.margin.jz.module.crm.dal.mysql.receivable;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.mybatis.core.mapper.BaseMapperX;
import cn.margin.jz.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.margin.jz.framework.mybatis.core.query.MPJLambdaWrapperX;
import cn.margin.jz.module.crm.controller.admin.receivable.vo.receivable.CrmReceivablePageReqVO;
import cn.margin.jz.module.crm.dal.dataobject.receivable.CrmReceivableDO;
import cn.margin.jz.module.crm.enums.common.CrmBizTypeEnum;
import cn.margin.jz.module.crm.util.CrmQueryWrapperUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 回款 Mapper
 *
 * @author 赤焰
 */
@Mapper
public interface CrmReceivableMapper extends BaseMapperX<CrmReceivableDO> {

    default int updateOwnerUserIdById(Long id, Long ownerUserId) {
        return update(new LambdaUpdateWrapper<CrmReceivableDO>()
                .eq(CrmReceivableDO::getId, id)
                .set(CrmReceivableDO::getOwnerUserId, ownerUserId));
    }

    default PageResult<CrmReceivableDO> selectPageByCustomerId(CrmReceivablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmReceivableDO>()
                .eq(CrmReceivableDO::getCustomerId, reqVO.getCustomerId()) // 必须传递
                .eqIfPresent(CrmReceivableDO::getNo, reqVO.getNo())
                .eqIfPresent(CrmReceivableDO::getPlanId, reqVO.getPlanId())
                .orderByDesc(CrmReceivableDO::getId));
    }

    default PageResult<CrmReceivableDO> selectPage(CrmReceivablePageReqVO pageReqVO, Long userId) {
        MPJLambdaWrapperX<CrmReceivableDO> query = new MPJLambdaWrapperX<>();
        // 拼接数据权限的查询条件
        CrmQueryWrapperUtils.appendPermissionCondition(query, CrmBizTypeEnum.CRM_RECEIVABLE.getType(),
                CrmReceivableDO::getId, userId, pageReqVO.getSceneType(), Boolean.FALSE);
        // 拼接自身的查询条件
        query.selectAll(CrmReceivableDO.class)
                .eqIfPresent(CrmReceivableDO::getNo, pageReqVO.getNo())
                .eqIfPresent(CrmReceivableDO::getPlanId, pageReqVO.getPlanId())
                .orderByDesc(CrmReceivableDO::getId);
        return selectJoinPage(pageReqVO, CrmReceivableDO.class, query);
    }

    default List<CrmReceivableDO> selectBatchIds(Collection<Long> ids, Long userId) {
        MPJLambdaWrapperX<CrmReceivableDO> query = new MPJLambdaWrapperX<>();
        // 拼接数据权限的查询条件
        CrmQueryWrapperUtils.appendPermissionCondition(query, CrmBizTypeEnum.CRM_RECEIVABLE.getType(), ids, userId);
        return selectJoinList(CrmReceivableDO.class, query);
    }

}
