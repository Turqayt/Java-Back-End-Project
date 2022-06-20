package com.j32bit.backend.entity;

import com.j32bit.backend.dto.VersionViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Log4j2
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Application.findAll", query = "SELECT u FROM Application u")
@Table(name = "application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "version_number")
    private String versionNumber;
    @Column(name = "logo")
    private String logo;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="application_id")
    private List<VersionViewDTO> version;
    @Column(name = "status")
    private byte status;
    @Column(name = "form_type")
    private byte formType;





    public Application(String description, String name, String shortName, Timestamp updatedAt, String updatedBy, String versionNumber,
                       byte formType) {

        this.createdAt = Timestamp.from(Instant.now());
        this.description = description;
        this.name = name;
        this.shortName = shortName;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.versionNumber = "1";
        this.logo = "null";
        this.status = 0;
        this.formType = formType;
        this.version = getVersion();

        log.info("Creating Form");

    }


}
