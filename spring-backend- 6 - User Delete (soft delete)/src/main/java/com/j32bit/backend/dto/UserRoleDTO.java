package com.j32bit.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.j32bit.backend.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

@NoArgsConstructor
public class UserRoleDTO {
    private  Integer id;
    private Integer role_id;
    private Integer user_id;
    public UserRoleDTO(Integer role_id, Integer user_id){
        this.role_id = role_id;
        this.user_id = user_id;
    }
    public static UserRoleDTO of(UserRole userRole){
        return new UserRoleDTO(userRole.getRole_id(), userRole.getUser_id());
    }
}
