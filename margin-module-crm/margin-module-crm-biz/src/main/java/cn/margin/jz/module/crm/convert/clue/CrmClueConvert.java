package cn.margin.jz.module.crm.convert.clue;

import cn.margin.jz.module.crm.controller.admin.clue.vo.CrmClueTransferReqVO;
import cn.margin.jz.module.crm.service.permission.bo.CrmPermissionTransferReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 线索 Convert
 *
 * @author Wanwan
 */
@Mapper
public interface CrmClueConvert {

    CrmClueConvert INSTANCE = Mappers.getMapper(CrmClueConvert.class);

    @Mapping(target = "bizId", source = "reqVO.id")
    CrmPermissionTransferReqBO convert(CrmClueTransferReqVO reqVO, Long userId);

}
