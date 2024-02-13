package cn.margin.jz.module.infra.convert.config;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.module.infra.controller.admin.config.vo.ConfigRespVO;
import cn.margin.jz.module.infra.controller.admin.config.vo.ConfigSaveReqVO;
import cn.margin.jz.module.infra.dal.dataobject.config.ConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConfigConvert {

    ConfigConvert INSTANCE = Mappers.getMapper(ConfigConvert.class);

    PageResult<ConfigRespVO> convertPage(PageResult<ConfigDO> page);

    List<ConfigRespVO> convertList(List<ConfigDO> list);

    @Mapping(source = "configKey", target = "key")
    ConfigRespVO convert(ConfigDO bean);

    @Mapping(source = "key", target = "configKey")
    ConfigDO convert(ConfigSaveReqVO bean);

}
