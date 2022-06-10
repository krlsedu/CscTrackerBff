package com.csctracker.bff.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {
    private List<ApplicationDetailDTO> list;
    private ApplicationDetailDTO last;
}
