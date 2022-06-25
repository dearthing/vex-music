package cn.dearth.vexmusic.music.dto;

import cn.dearth.vexmusic.music.enums.MusicStatus;
import lombok.Data;
import lombok.ToString;

/**
 * @author dearth
 */
@Data
@ToString(callSuper = true)
public class MusicDto {

    private String name;

    private MusicStatus status = MusicStatus.DRAFT;

    private String description;

}
