package cn.margin.jz.module.bpm.service.candidate.sourceInfoProcessor;

import cn.margin.jz.framework.common.util.collection.SetUtils;
import cn.margin.jz.module.bpm.controller.admin.candidate.vo.BpmTaskCandidateRuleVO;
import cn.margin.jz.module.bpm.enums.definition.BpmTaskAssignRuleTypeEnum;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfo;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfoProcessor;
import cn.margin.jz.module.system.api.user.AdminUserApi;
import org.flowable.engine.delegate.DelegateExecution;

import javax.annotation.Resource;
import java.util.Set;

public class BpmCandidateAdminUserApiSourceInfoProcessor implements BpmCandidateSourceInfoProcessor {
    @Resource
    private AdminUserApi api;

    @Override
    public Set<Integer> getSupportedTypes() {
        return SetUtils.asSet(BpmTaskAssignRuleTypeEnum.USER.getType());
    }

    @Override
    public void validRuleOptions(Integer type, Set<Long> options) {
        api.validateUserList(options);
    }

    @Override
    public Set<Long> doProcess(BpmCandidateSourceInfo request, BpmTaskCandidateRuleVO rule, DelegateExecution delegateExecution) {
        return rule.getOptions();
    }
}