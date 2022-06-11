package cn.dearth.vexmusic.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author dearth
 */
@MappedSuperclass
@Data
public abstract class AbstractEntity {


    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "cn.dearth.vexmusic.utils.KsuidIdentifierGenerator")
    private String id;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;


}
