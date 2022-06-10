package com.csctracker.bff.controller;

import com.csctracker.bff.dto.ApplicationDetailDTO;
import com.csctracker.bff.dto.ResponseDTO;
import com.csctracker.bff.service.DispachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ApplicationDetailController {

    private final DispachService dispachService;

    public ApplicationDetailController(DispachService dispachService) {
        this.dispachService = dispachService;
    }

    @PostMapping(value = "/usage-info", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final List<ApplicationDetailDTO> applicationDetailDTO) {
        dispachService.dispach(ApplicationDetailDTO.class);
    }

    @GetMapping(value = "/list-usage-info/{greaterId}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDTO> create(@PathVariable Long greaterId) {
        var response = dispachService.dispach(ResponseDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/reset", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void reset(@RequestBody List<ApplicationDetailDTO> applicationDetailDTOS) {
        dispachService.dispach(ApplicationDetailDTO.class);
    }

    @CrossOrigin
    @PostMapping(value = "/usage-info-single", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final ApplicationDetailDTO applicationDetailDTO) {
        dispachService.dispach(ApplicationDetailDTO.class);
    }

}
