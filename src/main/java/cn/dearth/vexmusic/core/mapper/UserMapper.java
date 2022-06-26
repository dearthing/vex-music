package cn.dearth.vexmusic.core.mapper;

import cn.dearth.vexmusic.core.dto.request.UserUpdateRequest;
import cn.dearth.vexmusic.core.entity.User;
import cn.dearth.vexmusic.core.dto.UserDto;
import cn.dearth.vexmusic.core.dto.request.UserCreateRequest;
import cn.dearth.vexmusic.core.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * @author dearth
 */
@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    // 从数据库中拿到的的实体转为Dto
    UserDto toDto(User user);

    // Dto转Vo输出给前端
    UserVo toVo(UserDto userDto);

    // userCreateRequest转User
    User createEntity(UserCreateRequest userCreateRequest);

    // 把UserUpdateRequest转成User 然后返回user
    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
