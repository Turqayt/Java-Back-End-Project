package com.j32bit.backend.service.implementation;

import com.j32bit.backend.dto.*;
import com.j32bit.backend.entity.User;
import com.j32bit.backend.entity.UserRole;
import com.j32bit.backend.repository.UserRepository;
import com.j32bit.backend.repository.UserRoleRepository;
import com.j32bit.backend.service.UserManagementService;
import com.j32bit.backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
* Kullanıcı ekleme, rol düzenleme, güncelleme ve "soft delete" silme işlemi bu serviste yapılıyor
* */

@Service
@RequiredArgsConstructor
@Log4j2
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;

    //Burada kullanıcı ve rol bilgileri ekleniyor
    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        final User user = userRepository.save(new User(userCreateDTO.getAddress(), userCreateDTO.getCompanyname(), userCreateDTO.getEmail(),
                userCreateDTO.getName(), userCreateDTO.getOccupation(), userCreateDTO.getPhonenumber(), userCreateDTO.getSurname(),
                userCreateDTO.getTcnumber(), userCreateDTO.getUsername()));
        log.info("User Created");
        UserRoleAddedDTO userRoleAddedDTO = new UserRoleAddedDTO();
        userRoleAddedDTO.setRole_id(2);
        userRoleAddedDTO.setUser_id(user.getId());
        userAuthorization(userRoleAddedDTO);
        log.info("Role added");
        return UserDTO.of(user);
    }

    //Kullanıcı bilgileri günceleniyor
    @Override
    @Transactional
    public UserDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
        log.info("User Found");
        user.setAddress(userUpdateDTO.getAddress());
        user.setCompanyName(userUpdateDTO.getCompanyname());
        user.setEmail(userUpdateDTO.getEmail());
        user.setName(userUpdateDTO.getName());
        user.setOccupation(userUpdateDTO.getOccupation());
        user.setPhoneNumber(userUpdateDTO.getPhonenumber());
        user.setSurname(userUpdateDTO.getSurname());
        user.setTcNumber(userUpdateDTO.getTcnumber());
        user.setUserName(userUpdateDTO.getUsername());
        log.info("User Values Assigned");
        final User updateUser = userRepository.save(user);
        log.info("User Data Saved in Database");
        return UserDTO.of(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id){
        userRepository.deleteById(id);

    }

    //Rol bilgileri günceleniyor
    @Override
    @Transactional
    public UserRoleDTO userAuthorization(UserRoleAddedDTO userRoleAddedDTO) {
        final UserRole userRole = userRoleRepository.save(new UserRole(userRoleAddedDTO.getRole_id(), userRoleAddedDTO.getUser_id()));
       return UserRoleDTO.of(userRole);
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) {
        userRoleRepository.deleteById(id);
    }


}
