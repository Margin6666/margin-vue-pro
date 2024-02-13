package cn.margin.jz.framework.idempotent.config;

import cn.margin.jz.framework.idempotent.core.aop.IdempotentAspect;
import cn.margin.jz.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import cn.margin.jz.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import cn.margin.jz.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import cn.margin.jz.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import cn.margin.jz.framework.redis.config.MarginRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = MarginRedisAutoConfiguration.class)
public class MarginIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
