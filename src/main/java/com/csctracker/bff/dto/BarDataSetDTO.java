package com.csctracker.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarDataSetDTO {
    private List<String> categories;
    private List<BarSerieDTO> series;
}
