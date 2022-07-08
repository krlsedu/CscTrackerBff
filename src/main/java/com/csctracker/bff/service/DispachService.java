package com.csctracker.bff.service;

import com.csctracker.bff.repository.RemoteRepository;
import com.csctracker.securitycore.service.UserInfoService;
import com.csctracker.service.RequestInfo;
import io.micrometer.core.annotation.Timed;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DispachService {
    private final UserInfoService userInfoService;
    private final RouteService routeService;

    private final RemoteRepository remoteRepository;

    public DispachService(UserInfoService userInfoService, RouteService routeService, RemoteRepository remoteRepository) {
        this.userInfoService = userInfoService;
        this.routeService = routeService;
        this.remoteRepository = remoteRepository;
    }

    @Timed(value = "greeting.time", description = "Time taken to return greeting")
    public String dispach() {
        var request = RequestInfo.getRequest();
        String url;
        switch (request.getMethod()) {
            case "GET":
                url = routeService.getUrl(RequestInfo.getPath());
                try {
                    return remoteRepository.dispachGet(url, userInfoService.getEmail());
                } catch (Exception e) {
                    return null;
                }
            case "POST":
                url = routeService.getUrl(RequestInfo.getPath());
                try {
                    return remoteRepository.dispachPost(url);
                } catch (Exception e) {
                    return null;
                }
            default:
                throw new RuntimeException("Exception: Method not supported");
        }
    }
}

