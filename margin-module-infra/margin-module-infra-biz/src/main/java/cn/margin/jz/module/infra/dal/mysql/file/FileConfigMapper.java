package cn.margin.jz.module.infra.dal.mysql.file;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.mybatis.core.mapper.BaseMapperX;
import cn.margin.jz.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.margin.jz.module.infra.controller.admin.file.vo.config.FileConfigPageReqVO;
import cn.margin.jz.module.infra.dal.dataobject.file.FileConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileConfigMapper extends BaseMapperX<FileConfigDO> {

    default PageResult<FileConfigDO> selectPage(FileConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FileConfigDO>()
                .likeIfPresent(FileConfigDO::getName, reqVO.getName())
                .eqIfPresent(FileConfigDO::getStorage, reqVO.getStorage())
                .betweenIfPresent(FileConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FileConfigDO::getId));
    }

    default FileConfigDO selectByMaster() {
        return selectOne(FileConfigDO::getMaster, true);
    }

}
