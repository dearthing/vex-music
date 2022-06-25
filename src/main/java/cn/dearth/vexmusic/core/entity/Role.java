package cn.dearth.vexmusic.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author dearth
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Role extends AbstractEntity {

    private String name;
    
    private String title;
}
