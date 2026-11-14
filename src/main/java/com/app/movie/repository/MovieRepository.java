package com.app.movie.repository;

import com.app.movie.domain.Genre;
import com.app.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public List<Movie> findMovieByGenre(Genre genre);
}
