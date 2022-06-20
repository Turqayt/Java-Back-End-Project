package com.j32bit.backend.dto;

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
@NamedQuery(name="VersionViewDTO.findAll", query="SELECT r FROM VersionViewDTO r")
@Table(name="version")
public class VersionViewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//otomatik artan deger
    private Integer id;
    @Column(name="version")
    private String version;



}
