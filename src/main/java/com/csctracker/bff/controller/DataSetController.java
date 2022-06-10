package com.csctracker.bff.controller;

import com.csctracker.bff.dto.DataSetDTO;
import com.csctracker.bff.dto.TimeLinePointDTO;
import com.csctracker.bff.service.DispachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataSetController {

    private final DispachService dispachService;

    public DataSetController(DispachService dispachService) {
        this.dispachService = dispachService;
    }

    @GetMapping(value = "/series", produces = "application/json")
    public ResponseEntity<List<TimeLinePointDTO>> getSeries(
            @RequestParam(value = "metric") String metric,
            @RequestParam(value = "period", required = false) String period) {
        return new ResponseEntity<>(dispachService.dispachList(TimeLinePointDTO.class), HttpStatus.OK);
    }

    @GetMapping(value = "/dataset", produces = "application/json")
    public ResponseEntity<List<DataSetDTO>> getDataSet(
            @RequestParam(value = "metric") String metric,
            @RequestParam(value = "period", required = false) String period,
            @RequestParam(value = "value", required = false) String value) {
        return new ResponseEntity<>(dispachService.dispachList(DataSetDTO.class), HttpStatus.OK);
    }
}
