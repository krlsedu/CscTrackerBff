package com.csctracker.bff.controller;

import com.csctracker.bff.dto.HeartbeatDTO;
import com.csctracker.bff.service.DispachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class HeartbeatController {

    private final DispachService dispachService;

    public HeartbeatController(DispachService dispachService) {
        this.dispachService = dispachService;
    }

    @GetMapping(value = "/heartbeats", produces = "application/json")
    public ResponseEntity<List<HeartbeatDTO>> getHeartbeat(
            @RequestParam(value = "dateIni", required = false) Date dateIni,
            @RequestParam(value = "dateEnd", required = false) Date dateEnd,
            @RequestParam(value = "period", required = false) String period) {
        return new ResponseEntity<>(dispachService.dispachList(HeartbeatDTO.class), HttpStatus.OK);
    }
}
