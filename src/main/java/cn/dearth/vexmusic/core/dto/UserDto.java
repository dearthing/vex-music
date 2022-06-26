package cn.dearth.vexmusic.core.dto;


import cn.dearth.vexmusic.core.enums.Gender;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用于业务层面会把密码给删除
 *
 * @author dearth
 */
@Data
public class UserDto {
    private String id;

    private String username;

    private String nickname;

    private List<RoleDto> roleVos;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;

}
