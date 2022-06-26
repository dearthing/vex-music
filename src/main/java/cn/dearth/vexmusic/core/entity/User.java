package cn.dearth.vexmusic.core.entity;

import cn.dearth.vexmusic.core.enums.Gender;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 把user独有的属性放在这一个类中
 *
 * @author dearth
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    @Column(unique = true)
    private String username;

    private String nickname;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // todo 这里是验证创建用户接口
    private Boolean locked = false;

    private Boolean enabled = true;

    private String lastLoginIp;

    private Date lastLoginTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // todo 帐号过期逻辑添加
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getLocked();
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
