package cn.margin.jz.framework.file.config;

import cn.margin.jz.framework.file.core.client.FileClientFactory;
import cn.margin.jz.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
 * @author 芋道源码
 */
@AutoConfiguration
public class MarginFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
