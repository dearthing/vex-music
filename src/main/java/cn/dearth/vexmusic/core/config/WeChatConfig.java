package cn.dearth.vexmusic.core.config;

import cn.dearth.vexmusic.core.config.properties.WeChatProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dearth
 */
@Configuration
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatConfig {

    private final WeChatProperties weChatProperties;

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(weChatProperties.getAppId());
        config.setSecret(weChatProperties.getAppSecret());
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

    @Autowired
    public WeChatConfig(WeChatProperties weChatProperties) {
        this.weChatProperties = weChatProperties;
    }
}
