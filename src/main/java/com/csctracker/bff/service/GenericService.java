package com.csctracker.bff.service;

import com.csctracker.bff.repository.RemoteRepository;
import com.csctracker.dto.Conversor;
import com.csctracker.dto.ServiceRegisterDTO;
import com.csctracker.securitycore.service.UserInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class GenericService {
    private final RemoteRepository remoteRepository;
    private final UserInfoService userInfoService;

    private final Conversor<ServiceRegisterDTO, ServiceRegisterDTO> conversor;

    public GenericService(RemoteRepository remoteRepository, UserInfoService userInfoService) {
        this.remoteRepository = remoteRepository;
        this.userInfoService = userInfoService;
        this.conversor = new Conversor<>(ServiceRegisterDTO.class, ServiceRegisterDTO.class);
    }

    public String save(String service) {
        return remoteRepository.dispachPost("http://datasource:5000/save?service=" + service, userInfoService.getEmail());
    }

    public String delete(String service) throws JsonProcessingException {
        return remoteRepository.dispachDelete("http://datasource:5000/delete?service=" + service, userInfoService.getEmail());
    }
}
