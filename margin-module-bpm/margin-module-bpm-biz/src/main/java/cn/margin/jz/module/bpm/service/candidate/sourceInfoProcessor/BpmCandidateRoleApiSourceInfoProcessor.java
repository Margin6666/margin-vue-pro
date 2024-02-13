package cn.margin.jz.module.bpm.service.candidate.sourceInfoProcessor;

import cn.margin.jz.framework.common.util.collection.SetUtils;
import cn.margin.jz.module.bpm.controller.admin.candidate.vo.BpmTaskCandidateRuleVO;
import cn.margin.jz.module.bpm.enums.definition.BpmTaskAssignRuleTypeEnum;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfo;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfoProcessor;
import cn.margin.jz.module.system.api.permission.PermissionApi;
import cn.margin.jz.module.system.api.permission.RoleApi;
import org.flowable.engine.delegate.DelegateExecution;

import javax.annotation.Resource;
import java.util.Set;

public class BpmCandidateRoleApiSourceInfoProcessor implements BpmCandidateSourceInfoProcessor {
    @Resource
    private RoleApi api;

    @Resource
    private PermissionApi permissionApi;

    @Override
    public Set<Integer> getSupportedTypes() {
        return SetUtils.asSet(BpmTaskAssignRuleTypeEnum.ROLE.getType());
    }

    @Override
    public void validRuleOptions(Integer type, Set<Long> options) {
        api.validRoleList(options);
    }

    @Override
    public Set<Long> doProcess(BpmCandidateSourceInfo request, BpmTaskCandidateRuleVO rule, DelegateExecution delegateExecution) {
        return permissionApi.getUserRoleIdListByRoleIds(rule.getOptions());
    }

}