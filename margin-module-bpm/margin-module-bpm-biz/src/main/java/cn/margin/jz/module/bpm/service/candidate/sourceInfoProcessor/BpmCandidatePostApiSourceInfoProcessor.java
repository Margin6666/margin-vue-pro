package cn.margin.jz.module.bpm.service.candidate.sourceInfoProcessor;

import cn.margin.jz.framework.common.util.collection.SetUtils;
import cn.margin.jz.module.bpm.controller.admin.candidate.vo.BpmTaskCandidateRuleVO;
import cn.margin.jz.module.bpm.enums.definition.BpmTaskAssignRuleTypeEnum;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfo;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfoProcessor;
import cn.margin.jz.module.system.api.dept.PostApi;
import cn.margin.jz.module.system.api.user.AdminUserApi;
import cn.margin.jz.module.system.api.user.dto.AdminUserRespDTO;
import org.flowable.engine.delegate.DelegateExecution;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static cn.margin.jz.framework.common.util.collection.CollectionUtils.convertSet;

public class BpmCandidatePostApiSourceInfoProcessor implements BpmCandidateSourceInfoProcessor {
    @Resource
    private PostApi api;
    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public Set<Integer> getSupportedTypes() {
        return SetUtils.asSet(BpmTaskAssignRuleTypeEnum.POST.getType());
    }

    @Override
    public void validRuleOptions(Integer type, Set<Long> options) {
        api.validPostList(options);
    }

    @Override
    public Set<Long> doProcess(BpmCandidateSourceInfo request, BpmTaskCandidateRuleVO rule, DelegateExecution delegateExecution) {
        List<AdminUserRespDTO> users = adminUserApi.getUserListByPostIds(rule.getOptions());
        return convertSet(users, AdminUserRespDTO::getId);
    }
}