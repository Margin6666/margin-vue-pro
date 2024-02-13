package cn.margin.jz.module.crm.service.message;

import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.module.crm.controller.admin.message.vo.CrmTodayCustomerPageReqVO;
import cn.margin.jz.module.crm.dal.dataobject.customer.CrmCustomerDO;

import javax.validation.Valid;

/**
 * CRM 代办消息 Service 接口
 *
 * @author dhb52
 */
public interface CrmMessageService {

    /**
     * TODO @dbh52：注释要写下
     *
     * @param pageReqVO
     * @return
     */
    PageResult<CrmCustomerDO> getTodayCustomerPage(@Valid CrmTodayCustomerPageReqVO pageReqVO, Long userId);

}
