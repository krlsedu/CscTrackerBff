package com.csctracker.bff.service;

import com.csctracker.bff.model.Route;
import com.csctracker.bff.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    Map<String, Route> routes;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        init();
    }

    public void init() {
        routes = new HashMap<>();
        List<Route> routeList = routeRepository.findAll();
        for (Route route : routeList) {
            routes.put(route.getPath(), route);
        }
    }

    public String getUrl(String path) {
        Route byPath = routes.get(path);
        if (byPath != null) {
            return byPath.getDestination() + byPath.getPath();
        } else {
            throw new IllegalArgumentException("Route not found");
        }
    }

    public void save(Route route) {
        routeRepository.save(route);
        routes.put(route.getPath(), route);
    }
}
