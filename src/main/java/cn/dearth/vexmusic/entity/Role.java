package cn.dearth.vexmusic.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author dearth
 */

@Data
@Entity
public class Role extends AbstractEntity {

    private String name;
    private String title;

}