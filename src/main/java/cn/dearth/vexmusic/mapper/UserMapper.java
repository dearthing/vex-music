package cn.dearth.vexmusic.mapper;

import cn.dearth.vexmusic.dto.UserCreateDto;
import cn.dearth.vexmusic.dto.UserDto;
import cn.dearth.vexmusic.entity.User;
import cn.dearth.vexmusic.vo.UserVo;
import org.mapstruct.Mapper;
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

    // userCreateDto转UserDto
    User createEntity(UserCreateDto userCreateDto);

}
