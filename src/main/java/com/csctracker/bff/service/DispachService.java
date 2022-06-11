package com.csctracker.bff.service;

import com.csctracker.securitycore.configs.UnAuthorized;
import com.csctracker.securitycore.dto.Conversor;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class DispachService {
    @Value("${backend.host}")
    private String backendHost;
    @Value("${backend.port}")
    private String backendPort;

    private static void checkResponse(HttpResponse<String> response) {
        if (response.getStatus() < 200 || response.getStatus() > 299) {
            switch (response.getStatus()) {
                case 401:
                case 403:
                    throw new UnAuthorized();
                case 404:
                    throw new RuntimeException("Not Found");
                default:
                    throw new RuntimeException("Internal Server Error");
            }
        }
    }

    public <Z> Z dispach(Class<Z> zClass) {
        var request = RequestInfo.getRequest();
        var conversor = new Conversor(zClass, zClass);
        switch (request.getMethod()) {
            case "GET":
                try {
                    return (Z) conversor.toD(dispachGet());
                } catch (Exception e) {
                    return null;
                }
            case "POST":
                try {
                    return (Z) conversor.toD(dispachPost());
                } catch (Exception e) {
                    return null;
                }
            default:
                throw new RuntimeException("Exception: Method not supported");
        }
    }

    public <Z> List<Z> dispachList(Class<Z> zClass) {
        var request = RequestInfo.getRequest();
        var conversor = new Conversor(zClass, zClass);
        switch (request.getMethod()) {
            case "GET":
                try {
                    return conversor.toDList(dispachGet());
                } catch (JsonSyntaxException e) {
                    //fixme: 
                    e.printStackTrace();
                    return new ArrayList<>();
                }
            case "POST":
                try {
                    return conversor.toDList(dispachPost());
                } catch (Exception e) {
                    return new ArrayList<>();
                }
            default:
                throw new RuntimeException("Method not supported");
        }
    }

    private String dispachGet() {

        String url = backendHost + ":" + backendPort + RequestInfo.getPath();
        var getRequest = Unirest.get(url);

        var headers = RequestInfo.getHeaders();
        for (var header : headers.entrySet()) {
            getRequest.header(header.getKey(), header.getValue());
        }

        var parameters = RequestInfo.getParameters();
        for (var parameter : parameters.entrySet()) {
            getRequest.queryString(parameter.getKey(), parameter.getValue());
        }

        HttpResponse<String> response = null;
        try {
            response = getRequest
                    .asString();
            checkResponse(response);
        } catch (UnirestException e) {
            throw new RuntimeException("Internal Server Error -> " + e.getMessage());
        }

        String body = response.getBody();
        log.info("Response: " + body);
        return body;
    }

    private String dispachPost() {
        String url = backendHost + ":" + backendPort + RequestInfo.getPath();

        var post = Unirest.post(url);

        var headers = RequestInfo.getHeaders();
        for (var header : headers.entrySet()) {
            switch (header.getKey().toLowerCase()) {
                case "content-type":
                case "authorization":
                    post.header(header.getKey(), header.getValue());
                    break;
                default:
                    break;
            }
        }


        HttpResponse<String> response = null;
        try {
            response = post
                    .body(RequestInfo.getBody())
                    .asString();
            checkResponse(response);
        } catch (UnirestException e) {
            throw new RuntimeException("Internal Server Error");
        }

        return response.getBody();
    }
}
