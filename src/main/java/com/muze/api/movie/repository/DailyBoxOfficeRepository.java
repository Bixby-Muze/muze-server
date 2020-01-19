package com.muze.api.movie.repository;

import com.muze.api.movie.entity.DailyBoxOffice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DailyBoxOfficeRepository extends PagingAndSortingRepository<DailyBoxOffice, String> {
}
