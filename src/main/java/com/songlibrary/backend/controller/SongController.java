package com.songlibrary.backend.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songlibrary.backend.model.Song;
import com.songlibrary.backend.repos.SongRepository;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    private boolean shouldFail() {
        return new Random().nextInt(5) == 0;
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createSong(Song song) {
        if (shouldFail()) {
            return ResponseEntity.status(500).body("Failed to add song. Please try again.");
        }
        songRepository.save(song);
        return ResponseEntity.ok(song);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSong(@PathVariable Long id, @RequestBody Song updatedSong) {
        if (shouldFail()) {
            return ResponseEntity.status(500).body("Failed to update song. Please try again.");
        }
        return songRepository.findById(id)
                .map(song -> {
                    song.setTitle(updatedSong.getTitle());
                    song.setArtist(updatedSong.getArtist());
                    song.setReleaseDate(updatedSong.getReleaseDate());
                    song.setPrice(updatedSong.getPrice());
                    songRepository.save(song);
                    return ResponseEntity.ok(song);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
