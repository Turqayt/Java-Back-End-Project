package com.j32bit.backend.dto;

import lombok.Data;

@Data
public class ApplicationCreateDTO {
    private String description;
    private String name;
    private byte formtype;
}
