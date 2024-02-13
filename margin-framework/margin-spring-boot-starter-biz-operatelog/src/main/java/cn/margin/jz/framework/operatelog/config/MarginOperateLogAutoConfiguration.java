package cn.margin.jz.framework.operatelog.config;

import cn.margin.jz.framework.operatelog.core.aop.OperateLogAspect;
import cn.margin.jz.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.margin.jz.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import cn.margin.jz.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class MarginOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
