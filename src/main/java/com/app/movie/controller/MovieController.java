package com.app.movie.controller;

import com.app.movie.dto.MovieDto;
import com.app.movie.domain.Genre;
import com.app.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies(){
            List<MovieDto> list = this.service.getAllMovies();
            return ResponseEntity.ok().body(list);
    }

    @GetMapping({"/{genre}", "/genre/{genre}"})
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@PathVariable String genre){
        try {
            Genre parsedGenre = Genre.valueOf(genre.trim().toUpperCase());
            List<MovieDto> list = this.service.getMoviesByGenre(parsedGenre);
            return ResponseEntity.ok().body(list);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
