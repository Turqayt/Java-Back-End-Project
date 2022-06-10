package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.Member;
import io.getarrays.userservice.repo.RoleRepo;
import io.getarrays.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public Member saveUser(Member user) {
        log.info("Saving new user {} to the database", user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
            log.info("Adding role {} to user {}", roleName, username);
            Member user = userRepo.findByUsername(username);
            Role role = roleRepo.findByName(roleName);
            user.getRoles().add(role);
    }

    @Override
    public Member getUser(String username) {
        log.info("Fetching user{}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<Member> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }
}
