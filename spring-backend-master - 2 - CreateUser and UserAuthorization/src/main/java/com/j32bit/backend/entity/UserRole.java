/**
 *
 */
package com.j32bit.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;


/**

 *
 */
@Log4j2
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u")
@Table(name = "userrole")
public class UserRole implements Serializable {


    @Id
    @Column(name = "role_id")
    private Integer role_id;

    @Column(name = "user_id")
    private Integer user_id;


}
