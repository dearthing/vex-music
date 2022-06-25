package cn.dearth.vexmusic.core.mapper;

import cn.dearth.vexmusic.core.dto.RoleDto;
import cn.dearth.vexmusic.core.entity.Role;
import cn.dearth.vexmusic.core.vo.RoleVo;
import cn.dearth.vexmusic.core.dto.request.RoleCreateRequest;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author dearth
 */
@Mapper(componentModel = "spring")
@Component
public interface RoleMapper {

    RoleVo toVo(RoleDto roleDto);

    Role createEntity(RoleCreateRequest roleCreateRequest);

    RoleDto toDto(Role role);
}
