package com.csctracker.bff.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDTO {
    private Long id;
    private String path;
    private String destination;
    private String method;
}
