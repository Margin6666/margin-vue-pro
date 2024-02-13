package cn.margin.jz.module.crm.dal.mysql.product;

import cn.margin.jz.framework.mybatis.core.mapper.BaseMapperX;
import cn.margin.jz.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.margin.jz.module.crm.controller.admin.product.vo.category.CrmProductCategoryListReqVO;
import cn.margin.jz.module.crm.dal.dataobject.product.CrmProductCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CRM 产品分类 Mapper
 *
 * @author ZanGe丶
 */
@Mapper
public interface CrmProductCategoryMapper extends BaseMapperX<CrmProductCategoryDO> {

    default List<CrmProductCategoryDO> selectList(CrmProductCategoryListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CrmProductCategoryDO>()
                .likeIfPresent(CrmProductCategoryDO::getName, reqVO.getName())
                .eqIfPresent(CrmProductCategoryDO::getParentId, reqVO.getParentId())
                .orderByDesc(CrmProductCategoryDO::getId));
    }

    default CrmProductCategoryDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(CrmProductCategoryDO::getParentId, parentId, CrmProductCategoryDO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(CrmProductCategoryDO::getParentId, parentId);
    }

}
