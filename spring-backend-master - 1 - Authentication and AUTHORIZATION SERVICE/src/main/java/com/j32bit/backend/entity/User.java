/**
 * 
 */
package com.j32bit.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**

 *
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "user_name"),
		@UniqueConstraint(columnNames = "email") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String address;


	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "tc_number")
	private String tcNumber;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "status")
	private byte status;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	private String name;

	private String surname;

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "reset_key")
	private String resetKey;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "is_admin")
	private byte isAdmin;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;



}
