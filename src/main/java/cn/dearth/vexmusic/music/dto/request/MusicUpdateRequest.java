package cn.dearth.vexmusic.music.dto.request;

import cn.dearth.vexmusic.music.enums.MusicStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author dearth
 */
@Data
public class MusicUpdateRequest {

    @NotBlank(message = "音乐名不能为空")
    private String name;

    private MusicStatus status = MusicStatus.DRAFT;

    private String description;
}
