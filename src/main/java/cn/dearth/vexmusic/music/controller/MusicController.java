package cn.dearth.vexmusic.music.controller;

import cn.dearth.vexmusic.music.dto.request.MusicCreateRequest;
import cn.dearth.vexmusic.music.dto.request.MusicUpdateRequest;
import cn.dearth.vexmusic.music.mapper.MusicMapper;
import cn.dearth.vexmusic.music.service.MusicService;
import cn.dearth.vexmusic.music.vo.MusicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * @author dearth
 */
@Api("音乐接口")
@RestController
@RequestMapping("/musics")
public class MusicController {

    MusicService musicService;

    MusicMapper musicMapper;

    @ApiOperation("添加音乐")
    @PostMapping("/")
    @RolesAllowed("ROLE_ADMIN")
    public MusicVo create(@RequestBody @Validated MusicCreateRequest musicCreateRequest) {
        return musicMapper.toVo(musicService.create(musicCreateRequest));
    }

    @ApiOperation("删除音乐")
    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public void delete(@PathVariable String id) {
        musicService.delete(id);
    }


    @ApiOperation("修改音乐")
    @PutMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public MusicVo update(@PathVariable String id,
                          @RequestBody MusicUpdateRequest musicUpdateRequest) {
        return musicMapper.toVo(musicService.update(id, musicUpdateRequest));
    }

    // Todo 参数问题
    @ApiOperation("检索音乐")
    @PostMapping("/search")
    public Page<MusicVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return musicService.search(pageable).map(musicMapper::toVo);
    }

    @PostMapping("/publish/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public void publish(@PathVariable String id) {
        musicService.publish(id);
    }

    @PostMapping("/close/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public void close(@PathVariable String id) {
        musicService.close(id);
    }


    @Autowired
    public void setMusicService(MusicService musicService) {
        this.musicService = musicService;
    }

    @Autowired
    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }
}
