package com.app.movie.service;

import com.app.movie.dto.MovieDto;
import com.app.movie.domain.Genre;
import com.app.movie.mapper.DtoMapper;
import com.app.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repo;

    public MovieService(MovieRepository repo){
        this.repo = repo;
    }

    public List<MovieDto> getAllMovies(){
        return this.repo
                .findAll()
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }

    public List<MovieDto> getMoviesByGenre(Genre genre){
        return this.repo
                .findMovieByGenre(genre)
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }

}
