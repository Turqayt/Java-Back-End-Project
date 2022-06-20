package com.j32bit.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.j32bit.backend.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private Integer id;
    private String description;
    private String name;
    private String logo;
    private List<VersionViewDTO> version;
    private String shortname;
    private byte formType;
    private Timestamp createdAt;
    private Timestamp updateAt;


    public static ApplicationDTO of(Application application){
        return new ApplicationDTO(application.getId(),application.getDescription(),application.getName(),application.getLogo(),
                application.getVersion(),application.getShortName(),application.getFormType(),application.getCreatedAt(),
                application.getUpdatedAt());
    }
}
