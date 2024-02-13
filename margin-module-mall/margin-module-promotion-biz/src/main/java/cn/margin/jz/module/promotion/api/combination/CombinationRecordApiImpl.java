package cn.margin.jz.module.promotion.api.combination;

import cn.margin.jz.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import cn.margin.jz.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import cn.margin.jz.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import cn.margin.jz.module.promotion.convert.combination.CombinationActivityConvert;
import cn.margin.jz.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import cn.margin.jz.module.promotion.enums.combination.CombinationRecordStatusEnum;
import cn.margin.jz.module.promotion.service.combination.CombinationRecordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.margin.jz.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.margin.jz.module.promotion.enums.ErrorCodeConstants.COMBINATION_RECORD_NOT_EXISTS;

/**
 * 拼团活动 API 实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class CombinationRecordApiImpl implements CombinationRecordApi {

    @Resource
    private CombinationRecordService combinationRecordService;

    @Override
    public void validateCombinationRecord(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        combinationRecordService.validateCombinationRecord(userId, activityId, headId, skuId, count);
    }

    @Override
    public CombinationRecordCreateRespDTO createCombinationRecord(CombinationRecordCreateReqDTO reqDTO) {
        return CombinationActivityConvert.INSTANCE.convert4(combinationRecordService.createCombinationRecord(reqDTO));
    }

    @Override
    public boolean isCombinationRecordSuccess(Long userId, Long orderId) {
        CombinationRecordDO record = combinationRecordService.getCombinationRecord(userId, orderId);
        if (record == null) {
            throw exception(COMBINATION_RECORD_NOT_EXISTS);
        }
        return CombinationRecordStatusEnum.isSuccess(record.getStatus());
    }

    @Override
    public CombinationValidateJoinRespDTO validateJoinCombination(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        return combinationRecordService.validateJoinCombination(userId, activityId, headId, skuId, count);
    }

}
