package com.csctracker.bff.repository;

import com.csctracker.bff.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customRouteRepository")
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByPath(String path);
}
