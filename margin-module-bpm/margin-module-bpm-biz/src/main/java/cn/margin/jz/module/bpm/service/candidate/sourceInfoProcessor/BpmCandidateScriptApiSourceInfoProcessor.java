package cn.margin.jz.module.bpm.service.candidate.sourceInfoProcessor;

import cn.hutool.core.collection.CollUtil;
import cn.margin.jz.framework.common.util.collection.CollectionUtils;
import cn.margin.jz.framework.common.util.collection.SetUtils;
import cn.margin.jz.module.bpm.controller.admin.candidate.vo.BpmTaskCandidateRuleVO;
import cn.margin.jz.module.bpm.enums.DictTypeConstants;
import cn.margin.jz.module.bpm.enums.definition.BpmTaskAssignRuleTypeEnum;
import cn.margin.jz.module.bpm.framework.flowable.core.behavior.script.BpmTaskAssignScript;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfo;
import cn.margin.jz.module.bpm.service.candidate.BpmCandidateSourceInfoProcessor;
import cn.margin.jz.module.system.api.dict.DictDataApi;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.ObjectProvider;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static cn.margin.jz.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.margin.jz.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.margin.jz.module.bpm.enums.ErrorCodeConstants.TASK_ASSIGN_SCRIPT_NOT_EXISTS;

public class BpmCandidateScriptApiSourceInfoProcessor implements BpmCandidateSourceInfoProcessor {
    @Resource
    private DictDataApi dictDataApi;

    /**
     * 任务分配脚本
     */
    private Map<Long, BpmTaskAssignScript> scriptMap = Collections.emptyMap();

    public void setScripts(ObjectProvider<BpmTaskAssignScript> scriptsOp) {
        List<BpmTaskAssignScript> scripts = scriptsOp.orderedStream().collect(Collectors.toList());
        setScripts(scripts);
    }

    public void setScripts(List<BpmTaskAssignScript> scripts) {
        this.scriptMap = convertMap(scripts, script -> script.getEnum().getId());
    }

    @Override
    public Set<Integer> getSupportedTypes() {
        return SetUtils.asSet(BpmTaskAssignRuleTypeEnum.SCRIPT.getType());
    }

    @Override
    public void validRuleOptions(Integer type, Set<Long> options) {
        dictDataApi.validateDictDataList(DictTypeConstants.TASK_ASSIGN_SCRIPT,
                CollectionUtils.convertSet(options, String::valueOf));
    }

    @Override
    public Set<Long> doProcess(BpmCandidateSourceInfo request, BpmTaskCandidateRuleVO rule, DelegateExecution delegateExecution) {
        return calculateTaskCandidateUsersByScript(delegateExecution, rule.getOptions());
    }

    private Set<Long> calculateTaskCandidateUsersByScript(DelegateExecution execution, Set<Long> options) {
        // 获得对应的脚本
        List<BpmTaskAssignScript> scripts = new ArrayList<>(options.size());
        options.forEach(id -> {
            BpmTaskAssignScript script = scriptMap.get(id);
            if (script == null) {
                throw exception(TASK_ASSIGN_SCRIPT_NOT_EXISTS, id);
            }
            scripts.add(script);
        });
        // 逐个计算任务
        Set<Long> userIds = new HashSet<>();
        scripts.forEach(script -> CollUtil.addAll(userIds, script.calculateTaskCandidateUsers(execution)));
        return userIds;
    }
}