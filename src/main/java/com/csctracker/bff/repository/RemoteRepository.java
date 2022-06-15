package com.csctracker.bff.repository;

import com.csctracker.configs.UnAuthorized;
import com.csctracker.service.RequestInfo;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.stereotype.Repository;

@Repository
public class RemoteRepository {
    public static void checkResponse(HttpResponse<String> response) {
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

    public String dispachGet(String url, String userName) {
        var getRequest = Unirest.get(url);

        var headers = RequestInfo.getHeaders();
        for (var header : headers.entrySet()) {
            getRequest.header(header.getKey(), header.getValue());
        }

        getRequest.header("userName", userName);

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

        return response.getBody();
    }

    public String dispachPost(String url) {

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
