package cn.margin.jz.module.crm.convert.customer;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.common.util.collection.CollectionUtils;
import cn.margin.jz.framework.common.util.object.BeanUtils;
import cn.margin.jz.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigRespVO;
import cn.margin.jz.module.crm.dal.dataobject.customer.CrmCustomerLimitConfigDO;
import cn.margin.jz.module.system.api.dept.dto.DeptRespDTO;
import cn.margin.jz.module.system.api.user.dto.AdminUserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * 客户限制配置 Convert
 *
 * @author Wanwan
 */
@Mapper
public interface CrmCustomerLimitConfigConvert {

    CrmCustomerLimitConfigConvert INSTANCE = Mappers.getMapper(CrmCustomerLimitConfigConvert.class);

    default PageResult<CrmCustomerLimitConfigRespVO> convertPage(
            PageResult<CrmCustomerLimitConfigDO> pageResult,
            Map<Long, AdminUserRespDTO> userMap, Map<Long, DeptRespDTO> deptMap) {
        List<CrmCustomerLimitConfigRespVO> list = CollectionUtils.convertList(pageResult.getList(),
                limitConfig -> convert(limitConfig, userMap, deptMap));
        return new PageResult<>(list, pageResult.getTotal());
    }

    default CrmCustomerLimitConfigRespVO convert(CrmCustomerLimitConfigDO limitConfig,
                                                 Map<Long, AdminUserRespDTO> userMap, Map<Long, DeptRespDTO> deptMap) {
        CrmCustomerLimitConfigRespVO limitConfigVO = BeanUtils.toBean(limitConfig, CrmCustomerLimitConfigRespVO.class);
        limitConfigVO.setUsers(CollectionUtils.convertList(limitConfigVO.getUserIds(), userMap::get));
        limitConfigVO.setDepts(CollectionUtils.convertList(limitConfigVO.getDeptIds(), deptMap::get));
        return limitConfigVO;
    }

}
