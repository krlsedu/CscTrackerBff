package com.csctracker.bff.service;

import com.csctracker.bff.repository.RemoteRepository;
import com.csctracker.dto.Conversor;
import com.csctracker.securitycore.service.UserInfoService;
import com.csctracker.service.RequestInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.micrometer.core.annotation.Timed;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class DispachService {
    private final UserInfoService userInfoService;
    @Value("${datasource.host}")
    private String datasourceHost;
    @Value("${datasource.port}")
    private String datasourcePort;

    private final RemoteRepository remoteRepository;

    public DispachService(UserInfoService userInfoService, RemoteRepository remoteRepository) {
        this.userInfoService = userInfoService;
        this.remoteRepository = remoteRepository;
    }

    @Timed(value = "greeting.time", description = "Time taken to return greeting")
    public <Z> Z dispach(Class<Z> zClass) {
        var request = RequestInfo.getRequest();
        var conversor = new Conversor(zClass, zClass);
        String url = datasourceHost + ":" + datasourcePort + RequestInfo.getPath();
        switch (request.getMethod()) {
            case "GET":
                try {
                    return (Z) conversor.toD(remoteRepository.dispachGet(url, userInfoService.getEmail()));
                } catch (Exception e) {
                    return null;
                }
            case "POST":
                try {
                    return (Z) conversor.toD(remoteRepository.dispachPost(url));
                } catch (JsonProcessingException e) {
                    return null;
                }
            default:
                throw new RuntimeException("Exception: Method not supported");
        }
    }

    @Timed(value = "greeting.time", description = "Time taken to return greeting")
    public <Z> List<Z> dispachList(Class<Z> zClass) {
        var request = RequestInfo.getRequest();
        var conversor = new Conversor(zClass, zClass);
        String url = datasourceHost + ":" + datasourcePort + RequestInfo.getPath();
        switch (request.getMethod()) {
            case "GET":
                try {
                    return conversor.toDList(remoteRepository.dispachGet(url, userInfoService.getEmail()));
                } catch (JsonProcessingException e) {
                    //fixme: 
                    e.printStackTrace();
                    return new ArrayList<>();
                }
            case "POST":
                try {
                    return conversor.toDList(remoteRepository.dispachPost(url));
                } catch (JsonProcessingException e) {
                    return new ArrayList<>();
                }
            default:
                throw new RuntimeException("Method not supported");
        }
    }
}
