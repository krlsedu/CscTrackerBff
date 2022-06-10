package com.csctracker.bff.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ApplicationDetailDTO implements ModelDTO {

    private Long id;
    private String name;
    private String activityDetail;
    private Long timeSpentMillis;
    private Date dateIni;
    private Date dateEnd;
    private String osName;
    private String hostName;
    private String pluginName;

    private String processName;
    private String completeProcessName;
    private String appVersion;

    private String project;
    private String language;
    private String category;
    private String ideName;
    private String ideVersion;
    private String entityType;
    private String entity;
    private String url;
    private String domain;

    private Long timeSpentMillisNoConflict;
}
