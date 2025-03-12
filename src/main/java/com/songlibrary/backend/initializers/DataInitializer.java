package com.songlibrary.backend.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.songlibrary.backend.model.Song;
import com.songlibrary.backend.repos.SongRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SongRepository songRepository;

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        songRepository.deleteAll();

        // Insert initial data
        songRepository.saveAll(Arrays.asList(
                new Song("Bohemian Rhapsody", "Queen", parseDate("1975-10-31"), 1.29),
                new Song("Hotel California", "Eagles", parseDate("1977-02-26"), 1.49),
                new Song("Smells Like Teen Spirit", "Nirvana", parseDate("1991-09-10"), 1.19),
                new Song("Rolling in the Deep", "Adele", parseDate("2010-11-29"), 1.39),
                new Song("Shape of You", "Ed Sheeran", parseDate("2017-01-06"), 1.59)));

        System.out.println("Initial data inserted into the database.");
    }

    // Helper method to parse date strings
    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + dateStr, e);
        }
    }
}