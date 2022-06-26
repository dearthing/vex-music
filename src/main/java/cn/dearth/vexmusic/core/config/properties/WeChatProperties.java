package cn.dearth.vexmusic.core.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dearth
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.mp")
@Component
public class WeChatProperties {

    private String appId;

    private String appSecret;

}
