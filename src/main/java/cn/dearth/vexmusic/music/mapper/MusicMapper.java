package cn.dearth.vexmusic.music.mapper;

import cn.dearth.vexmusic.music.dto.MusicDto;
import cn.dearth.vexmusic.music.dto.request.MusicCreateRequest;
import cn.dearth.vexmusic.music.dto.request.MusicUpdateRequest;
import cn.dearth.vexmusic.music.entity.Music;
import cn.dearth.vexmusic.music.vo.MusicVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * @author dearth
 */
@Mapper(componentModel = "spring")
@Component
public interface MusicMapper {

    MusicDto toDto(Music music);

    MusicVo toVo(MusicDto musicDto);

    Music createEntity(MusicCreateRequest musicCreateRequest);

    Music updateEntity(@MappingTarget Music music, MusicUpdateRequest musicUpdateRequest);
}
