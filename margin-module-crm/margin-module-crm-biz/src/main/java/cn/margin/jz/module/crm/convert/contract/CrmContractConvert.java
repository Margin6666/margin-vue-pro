package cn.margin.jz.module.crm.convert.contract;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.common.util.object.BeanUtils;
import cn.margin.jz.module.crm.controller.admin.contract.vo.CrmContractRespVO;
import cn.margin.jz.module.crm.controller.admin.contract.vo.CrmContractTransferReqVO;
import cn.margin.jz.module.crm.dal.dataobject.contract.CrmContractDO;
import cn.margin.jz.module.crm.dal.dataobject.customer.CrmCustomerDO;
import cn.margin.jz.module.crm.service.permission.bo.CrmPermissionTransferReqBO;
import cn.margin.jz.module.system.api.user.dto.AdminUserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static cn.margin.jz.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.margin.jz.framework.common.util.collection.MapUtils.findAndThen;

/**
 * 合同 Convert
 *
 * @author dhb52
 */
@Mapper
public interface CrmContractConvert {

    CrmContractConvert INSTANCE = Mappers.getMapper(CrmContractConvert.class);

    @Mapping(target = "bizId", source = "reqVO.id")
    CrmPermissionTransferReqBO convert(CrmContractTransferReqVO reqVO, Long userId);

    default PageResult<CrmContractRespVO> convertPage(PageResult<CrmContractDO> pageResult, Map<Long, AdminUserRespDTO> userMap,
                                                      List<CrmCustomerDO> customerList) {
        PageResult<CrmContractRespVO> voPageResult = BeanUtils.toBean(pageResult, CrmContractRespVO.class);
        // 拼接关联字段
        Map<Long, CrmCustomerDO> customerMap = convertMap(customerList, CrmCustomerDO::getId);
        voPageResult.getList().forEach(contract -> {
            findAndThen(userMap, contract.getOwnerUserId(), user -> contract.setOwnerUserName(user.getNickname()));
            findAndThen(userMap, Long.parseLong(contract.getCreator()), user -> contract.setCreatorName(user.getNickname()));
            findAndThen(customerMap, contract.getCustomerId(), customer -> contract.setCustomerName(customer.getName()));
        });
        return voPageResult;
    }

}
