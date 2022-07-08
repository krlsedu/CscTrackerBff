package com.csctracker.bff.controller;

import com.csctracker.bff.service.GenericService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenericController {

    private final GenericService genericService;

    public GenericController(GenericService genericService) {
        this.genericService = genericService;
    }

    @PostMapping("/{service}/save")
    public ResponseEntity<String> save(@RequestBody String body, @PathVariable String service) throws JsonProcessingException {
        return ResponseEntity.ok(genericService.save(service));
    }

    @DeleteMapping("/{service}/delete")
    public ResponseEntity<String> delete(@RequestBody String body, @PathVariable String service) throws JsonProcessingException {
        return ResponseEntity.ok(genericService.delete(service));
    }

    @GetMapping("/{service}/list")
    public ResponseEntity<String> list(@PathVariable String service) {
        return ResponseEntity.ok("list");
    }
}
