package com.app.movie.mapper;

import com.app.movie.dto.MovieDto;
import com.app.movie.entity.Movie;

public class DtoMapper {

    public static MovieDto toDto(Movie movie){
        return new MovieDto(movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getGenre());
    }
}
