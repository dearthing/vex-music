package cn.dearth.vexmusic.entity;

import cn.dearth.vexmusic.enums.Gender;
import cn.dearth.vexmusic.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 把user独有的属性放在这一个类中
 * @author dearth
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{

    @Column(unique = true)
    private String username;

    private String nickname;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;


}
