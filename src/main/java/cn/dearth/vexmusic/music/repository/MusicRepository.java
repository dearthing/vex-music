package cn.dearth.vexmusic.music.repository;

import cn.dearth.vexmusic.music.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dearth
 */
public interface MusicRepository extends JpaRepository<Music, String> {
}
