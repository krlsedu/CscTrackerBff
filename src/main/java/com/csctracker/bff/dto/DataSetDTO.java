package com.csctracker.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DataSetDTO {
    private String label;
    private BigDecimal value;
}
