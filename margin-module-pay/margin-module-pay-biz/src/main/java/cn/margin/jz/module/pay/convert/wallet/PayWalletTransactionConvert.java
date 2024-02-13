package cn.margin.jz.module.pay.convert.wallet;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;
import cn.margin.jz.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import cn.margin.jz.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import cn.margin.jz.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletTransactionConvert {

    PayWalletTransactionConvert INSTANCE = Mappers.getMapper(PayWalletTransactionConvert.class);

    PageResult<PayWalletTransactionRespVO> convertPage2(PageResult<PayWalletTransactionDO> page);

    PayWalletTransactionDO convert(WalletTransactionCreateReqBO bean);

}
