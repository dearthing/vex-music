package cn.dearth.vexmusic.dto;

import cn.dearth.vexmusic.vo.RoleVo;
import lombok.Data;

import java.util.List;

/**
 * @author dearth
 */
@Data
public class UserDto {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roleVos;

}
