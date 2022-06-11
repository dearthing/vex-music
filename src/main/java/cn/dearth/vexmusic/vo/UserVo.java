package cn.dearth.vexmusic.vo;

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

    private List<RoleVo> roleVos;

}
