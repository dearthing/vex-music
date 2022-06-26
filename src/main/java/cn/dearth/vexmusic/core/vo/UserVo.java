package cn.dearth.vexmusic.core.vo;

import cn.dearth.vexmusic.core.enums.Gender;
import lombok.Data;

import java.util.List;

/**
 * @author dearth
 */
@Data
public class UserVo {

    private String id;

    private String username;

    private String nickname;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private List<RoleVo> roleVos;


}
