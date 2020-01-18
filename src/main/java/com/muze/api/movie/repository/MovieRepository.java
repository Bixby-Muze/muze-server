package com.muze.api.movie.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.muze.api.movie.entity.Movie;

@RepositoryRestResource
public interface MovieRepository extends PagingAndSortingRepository<Movie, String> {
}
