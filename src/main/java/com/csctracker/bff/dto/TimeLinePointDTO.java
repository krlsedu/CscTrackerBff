package com.csctracker.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeLinePointDTO {
    private String x;
    private Long[] y;
}
