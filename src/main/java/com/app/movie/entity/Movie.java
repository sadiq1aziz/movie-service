package com.app.movie.entity;

import com.app.movie.domain.Genre;
import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator",
            sequenceName = "sequence_generator",
            allocationSize = 10)
    private Integer id;
    private String title;
    private Integer releaseYear;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }
}
