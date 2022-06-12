/**
 * 
 */
package com.j32bit.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer id;
	private String email;
	private String name;
	private String surname;
	private String userName;
	private String tcNumber;
	private String companyName;
	private String occupation;
	private String phoneNumber;
	private String address;
	private byte isAdmin;
	private byte status;
	private List<RoleDTO> roles;


}
