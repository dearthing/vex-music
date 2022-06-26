package cn.dearth.vexmusic.core.dto.request;

import cn.dearth.vexmusic.core.enums.Gender;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author dearth
 */
@Data
public class UserUpdateRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "用户名长度必须在4到64个字符之间")
    private String username;

    private String nickname;

    private Gender gender;
}
