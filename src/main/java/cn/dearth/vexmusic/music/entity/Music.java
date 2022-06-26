package cn.dearth.vexmusic.music.entity;

import cn.dearth.vexmusic.core.entity.AbstractEntity;
import cn.dearth.vexmusic.music.enums.MusicStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author dearth
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Music extends AbstractEntity {

    private String name;

    private MusicStatus status;

    private String description;

}
