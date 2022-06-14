package cn.dearth.vexmusic.entity;

import cn.dearth.vexmusic.enums.Gender;
import cn.dearth.vexmusic.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
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
public class User extends AbstractEntity implements UserDetails {

    @Column(unique = true)
    private String username;

    private String nickname = "vex";

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<Role> roles; // todo 无法toString() 问题

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // todo 这里是验证创建用户接口
    private Boolean locked = false;

    private Boolean enabled = true;

    private String lastLoginIp;

    private Date lastLoginTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        // todo 帐号过期逻辑添加
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // todo 账号认证过期逻辑添加
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled();
    }
}
