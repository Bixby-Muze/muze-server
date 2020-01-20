package com.muze.api.movie.repository;

import com.muze.api.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Movie findByCode(String code);

    @Async
    public Movie save(Movie movie);
}
