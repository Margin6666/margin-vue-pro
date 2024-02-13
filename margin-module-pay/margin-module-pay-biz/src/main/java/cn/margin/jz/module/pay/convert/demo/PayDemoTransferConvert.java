package cn.margin.jz.module.pay.convert.demo;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import cn.margin.jz.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import cn.margin.jz.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jason
 */
@Mapper
public interface PayDemoTransferConvert {

    PayDemoTransferConvert INSTANCE = Mappers.getMapper(PayDemoTransferConvert.class);

    PayDemoTransferDO convert(PayDemoTransferCreateReqVO bean);

    PageResult<PayDemoTransferRespVO> convertPage(PageResult<PayDemoTransferDO> pageResult);
}
