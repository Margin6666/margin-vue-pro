package cn.margin.jz.module.bpm.convert.definition;

import cn.margin.jz.module.bpm.controller.admin.definition.vo.form.BpmFormCreateReqVO;
import cn.margin.jz.module.bpm.controller.admin.definition.vo.form.BpmFormRespVO;
import cn.margin.jz.module.bpm.controller.admin.definition.vo.form.BpmFormSimpleRespVO;
import cn.margin.jz.module.bpm.controller.admin.definition.vo.form.BpmFormUpdateReqVO;
import cn.margin.jz.module.bpm.dal.dataobject.definition.BpmFormDO;
import cn.margin.jz.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动态表单 Convert
 *
 * @author 芋艿
 */
@Mapper
public interface BpmFormConvert {

    BpmFormConvert INSTANCE = Mappers.getMapper(BpmFormConvert.class);

    BpmFormDO convert(BpmFormCreateReqVO bean);

    BpmFormDO convert(BpmFormUpdateReqVO bean);

    BpmFormRespVO convert(BpmFormDO bean);

    List<BpmFormSimpleRespVO> convertList2(List<BpmFormDO> list);

    PageResult<BpmFormRespVO> convertPage(PageResult<BpmFormDO> page);

}
