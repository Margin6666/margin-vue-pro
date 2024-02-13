package cn.margin.jz.module.pay.convert.transfer;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.pay.core.client.dto.transfer.PayTransferUnifiedReqDTO;
import cn.margin.jz.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import cn.margin.jz.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import cn.margin.jz.module.pay.controller.admin.transfer.vo.PayTransferCreateReqVO;
import cn.margin.jz.module.pay.controller.admin.transfer.vo.PayTransferPageItemRespVO;
import cn.margin.jz.module.pay.controller.admin.transfer.vo.PayTransferRespVO;
import cn.margin.jz.module.pay.dal.dataobject.transfer.PayTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransferConvert {

    PayTransferConvert INSTANCE = Mappers.getMapper(PayTransferConvert.class);

    PayTransferDO convert(PayTransferCreateReqDTO dto);

    PayTransferUnifiedReqDTO convert2(PayTransferDO dto);

    PayTransferCreateReqDTO convert(PayTransferCreateReqVO vo);

    PayTransferCreateReqDTO convert(PayDemoTransferCreateReqVO vo);

    PayTransferRespVO  convert(PayTransferDO bean);

    PageResult<PayTransferPageItemRespVO> convertPage(PageResult<PayTransferDO> pageResult);
}
