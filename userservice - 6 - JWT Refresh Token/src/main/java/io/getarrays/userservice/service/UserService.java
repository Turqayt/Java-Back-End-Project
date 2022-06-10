package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.Member;

import java.util.List;

public interface UserService {
    Member saveUser(Member user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Member getUser(String username);
    List<Member> getUsers();
}
