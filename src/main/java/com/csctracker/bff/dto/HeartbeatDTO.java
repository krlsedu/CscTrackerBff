package com.csctracker.bff.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HeartbeatDTO {
    private String entity;
    private String process;
    private String applicationName;
    private String entityType;
    private BigDecimal timestamp;
    private boolean write;
    private String project;
    private String language;
    private String category;
    private String ideName;
    private String ideVersion;
    private String hostName;
    private String osName;
    private Long timeSpentMillis;
    private Long timeSpentMillisNoConflict;
    private Date dateTime;
    private Date dateTimeEnd;
    private boolean sent;
    private String domain;
}
