package cn.dearth.vexmusic.music.service;

import cn.dearth.vexmusic.music.dto.MusicDto;
import cn.dearth.vexmusic.music.dto.request.MusicCreateRequest;
import cn.dearth.vexmusic.music.dto.request.MusicUpdateRequest;

/**
 * @author dearth
 */
public interface MusicService {


    MusicDto create(MusicCreateRequest musicCreateRequest);

    void delete(String id);

    MusicDto update(String id, MusicUpdateRequest musicUpdateRequest);
}
