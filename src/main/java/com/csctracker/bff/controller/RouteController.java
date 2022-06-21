package com.csctracker.bff.controller;

import com.csctracker.bff.dto.RouteDTO;
import com.csctracker.bff.model.Route;
import com.csctracker.bff.service.RouteService;
import com.csctracker.dto.Conversor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    private final RouteService routeService;

    private final Conversor<Route, RouteDTO> conversor;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
        this.conversor = new Conversor<>(Route.class, RouteDTO.class);
    }

    @PostMapping("/route")
    public void save(@RequestBody RouteDTO routeDTO) {
        var route = conversor.toE(routeDTO);
        routeService.save(route);
    }
}
