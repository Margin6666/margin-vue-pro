package cn.margin.jz.module.bpm.service.cc;

import cn.margin.jz.framework.test.core.ut.BaseDbUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

@Import({BpmProcessInstanceCopyServiceImpl.class})
class BpmProcessInstanceCopyServiceTest extends BaseDbUnitTest {
    @Resource
    private BpmProcessInstanceCopyServiceImpl service;

    @Test
    void queryById() {
    }
}