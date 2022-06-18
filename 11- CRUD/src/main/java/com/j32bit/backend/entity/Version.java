package com.j32bit.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Version.findAll", query="SELECT r FROM Version r")
@Table(name="version")
public class Version implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//otomatik artan deger
    private Integer id;
    @Column(name="version")
    private String version;

    @Column(name = "application_id")
    private int applicationId;

    public Version(String version, int applicationId) {
        this.version = version;
        this.applicationId = applicationId;
    }
}