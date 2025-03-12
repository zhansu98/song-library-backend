package com.songlibrary.backend.repos;

import com.songlibrary.backend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

}
