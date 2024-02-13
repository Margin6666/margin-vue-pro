package cn.margin.jz.module.pay.convert.wallet;

import cn.hutool.core.collection.CollUtil;
import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.common.util.collection.CollectionUtils;
import cn.margin.jz.framework.common.util.collection.MapUtils;
import cn.margin.jz.framework.common.util.object.BeanUtils;
import cn.margin.jz.framework.dict.core.util.DictFrameworkUtils;
import cn.margin.jz.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateRespVO;
import cn.margin.jz.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeRespVO;
import cn.margin.jz.module.pay.dal.dataobject.order.PayOrderDO;
import cn.margin.jz.module.pay.dal.dataobject.wallet.PayWalletRechargeDO;
import cn.margin.jz.module.pay.enums.DictTypeConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface PayWalletRechargeConvert {

    PayWalletRechargeConvert INSTANCE = Mappers.getMapper(PayWalletRechargeConvert.class);

    @Mapping(target = "totalPrice", expression = "java( payPrice + bonusPrice)")
    PayWalletRechargeDO convert(Long walletId, Integer payPrice, Integer bonusPrice, Long packageId);

    AppPayWalletRechargeCreateRespVO convert(PayWalletRechargeDO bean);

    default PageResult<AppPayWalletRechargeRespVO> convertPage(PageResult<PayWalletRechargeDO> pageResult,
                                                               List<PayOrderDO> payOrderList) {
        PageResult<AppPayWalletRechargeRespVO> voPageResult = BeanUtils.toBean(pageResult, AppPayWalletRechargeRespVO.class);
        Map<Long, PayOrderDO> payOrderMap = CollectionUtils.convertMap(payOrderList, PayOrderDO::getId);
        voPageResult.getList().forEach(recharge -> {
            recharge.setPayChannelName(DictFrameworkUtils.getDictDataLabel(
                    DictTypeConstants.CHANNEL_CODE, recharge.getPayChannelCode()));
            MapUtils.findAndThen(payOrderMap, recharge.getPayOrderId(),
                    order -> recharge.setPayOrderChannelOrderNo(order.getChannelOrderNo()));
        });
        return voPageResult;
    }

}
