package cn.dearth.vexmusic.music.service;

import cn.dearth.vexmusic.music.dto.MusicDto;
import cn.dearth.vexmusic.music.dto.request.MusicCreateRequest;
import cn.dearth.vexmusic.music.dto.request.MusicUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dearth
 */
public interface MusicService {


    MusicDto create(MusicCreateRequest musicCreateRequest);

    void delete(String id);

    MusicDto update(String id, MusicUpdateRequest musicUpdateRequest);

    Page<MusicDto> search(Pageable pageable);

    void publish(String id);

    void close(String id);
}
