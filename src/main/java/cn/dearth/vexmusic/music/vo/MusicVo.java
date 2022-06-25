package cn.dearth.vexmusic.music.vo;

import cn.dearth.vexmusic.music.enums.MusicStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author dearth
 */
@Data
public class MusicVo {

    private String name;

    private String description;

    private MusicStatus status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date createdTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date updatedTime;
}
