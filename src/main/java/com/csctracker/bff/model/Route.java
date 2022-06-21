package com.csctracker.bff.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String path;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String destination;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String method;
}
