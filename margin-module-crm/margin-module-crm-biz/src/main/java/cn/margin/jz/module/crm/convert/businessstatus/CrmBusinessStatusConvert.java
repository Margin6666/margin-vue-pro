package cn.margin.jz.module.crm.convert.businessstatus;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.module.crm.controller.admin.business.vo.status.CrmBusinessStatusRespVO;
import cn.margin.jz.module.crm.dal.dataobject.business.CrmBusinessStatusDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商机状态 Convert
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessStatusConvert {

    CrmBusinessStatusConvert INSTANCE = Mappers.getMapper(CrmBusinessStatusConvert.class);

    List<CrmBusinessStatusRespVO> convertList(List<CrmBusinessStatusDO> list);

    PageResult<CrmBusinessStatusRespVO> convertPage(PageResult<CrmBusinessStatusDO> page);

}
