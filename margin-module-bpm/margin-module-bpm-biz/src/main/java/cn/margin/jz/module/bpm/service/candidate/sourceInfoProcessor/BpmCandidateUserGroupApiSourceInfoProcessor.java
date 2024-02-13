package cn.margin.jz.module.bpm.service.candidate.sourceInfoProcessor;

import cn.margin.jz.framework.common.util.collection.SetUtils;
import cn.margin.jz.module.bpm.controller.admin.candidate.vo.BpmTaskCandidateRuleVO;
import cn.margin.jz.module.bpm.dal.dataobject.definition.BpmUserGroupDO;
import cn.margin.jz.module.bpm.enums.definition.BpmTaskAssignRuleTypeEnum;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfo;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfoProcessor;
import cn.margin.jz.module.bpm.service.definition.BpmUserGroupService;
import org.flowable.engine.delegate.DelegateExecution;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BpmCandidateUserGroupApiSourceInfoProcessor implements BpmCandidateSourceInfoProcessor {
    @Resource
    private BpmUserGroupService api;
    @Resource
    private BpmUserGroupService userGroupService;

    @Override
    public Set<Integer> getSupportedTypes() {
        return SetUtils.asSet(BpmTaskAssignRuleTypeEnum.USER_GROUP.getType());
    }

    @Override
    public void validRuleOptions(Integer type, Set<Long> options) {
        api.validUserGroups(options);
    }

    @Override
    public Set<Long> doProcess(BpmCandidateSourceInfo request, BpmTaskCandidateRuleVO rule, DelegateExecution delegateExecution) {
        List<BpmUserGroupDO> userGroups = userGroupService.getUserGroupList(rule.getOptions());
        Set<Long> userIds = new HashSet<>();
        userGroups.forEach(group -> userIds.addAll(group.getMemberUserIds()));
        return userIds;
    }

}