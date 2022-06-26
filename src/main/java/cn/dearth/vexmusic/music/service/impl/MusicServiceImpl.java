package cn.dearth.vexmusic.music.service.impl;

import cn.dearth.vexmusic.core.exception.BizException;
import cn.dearth.vexmusic.core.exception.ExceptionType;
import cn.dearth.vexmusic.music.dto.MusicDto;
import cn.dearth.vexmusic.music.dto.request.MusicCreateRequest;
import cn.dearth.vexmusic.music.dto.request.MusicUpdateRequest;
import cn.dearth.vexmusic.music.entity.Music;
import cn.dearth.vexmusic.music.enums.MusicStatus;
import cn.dearth.vexmusic.music.mapper.MusicMapper;
import cn.dearth.vexmusic.music.repository.MusicRepository;
import cn.dearth.vexmusic.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author dearth
 */
@Service
public class MusicServiceImpl implements MusicService {


    private MusicRepository musicRepository;

    private MusicMapper musicMapper;


    @Override
    public MusicDto create(MusicCreateRequest musicCreateRequest) {
        Music music = musicMapper.createEntity(musicCreateRequest);
        music.setStatus(MusicStatus.PUBLISHED);
        return musicMapper.toDto(musicRepository.save(music));
    }

    @Override
    public void delete(String id) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (!optionalMusic.isPresent()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        musicRepository.deleteById(id);
    }

    @Override
    public MusicDto update(String id, MusicUpdateRequest musicUpdateRequest) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (!optionalMusic.isPresent()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        return musicMapper.toDto(musicMapper.updateEntity(optionalMusic.get(), musicUpdateRequest));
    }

    @Override
    public Page<MusicDto> search(Pageable pageable) {
        return musicRepository.findAll(pageable).map(musicMapper::toDto);
    }

    // TODO 需要把重复代码抽离到一个公共的Service
    @Override
    public void publish(String id) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (!optionalMusic.isPresent()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        Music music = optionalMusic.get();
        music.setStatus(MusicStatus.PUBLISHED);
        musicRepository.save(music);
    }

    // TODO 需要把重复代码抽离到一个公共的Service
    @Override
    public void close(String id) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (!optionalMusic.isPresent()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        Music music = optionalMusic.get();
        music.setStatus(MusicStatus.CLOSED);
        musicRepository.save(music);
    }


    @Autowired
    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Autowired
    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }
}
