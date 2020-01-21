package com.muze.api.movie.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DailyBoxOfficeListDto {

    private List<DailyBoxOfficeDto> dailyBoxOfficeDtoList;
}
