package cn.margin.jz.module.crm.controller.admin.receivable.vo.plan;

import cn.margin.jz.framework.common.pojo.PageParam;
import cn.margin.jz.framework.common.validation.InEnum;
import cn.margin.jz.module.crm.enums.common.CrmSceneTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - CRM 回款计划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmReceivablePlanPageReqVO extends PageParam {

    @Schema(description = "客户编号", example = "18026")
    private Long customerId;

    // TODO @芋艿：这个搜的应该是合同编号 no
    @Schema(description = "合同名称", example = "3473")
    private Long contractId;

    @Schema(description = "场景类型", example = "1")
    @InEnum(CrmSceneTypeEnum.class)
    private Integer sceneType; // 场景类型，为 null 时则表示全部

}
